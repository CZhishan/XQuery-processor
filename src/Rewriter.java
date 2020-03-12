import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Rewriter {

    public String Rewriting(XQueryParser.Flwr_clauseContext ctx){
        String output = "";

        // for clause
        int numFor;
        numFor = ctx.forClause().var().size();
        List<HashSet<String>> classes = new LinkedList<HashSet<String>>();
        for(int i=0; i < numFor;i++) {
            String key = ctx.forClause().var(i).getText();
            String parent = ctx.forClause().xq(i).getText().split("/")[0];
            int size = classes.size();
            boolean found = false;
            // construct the classification
            for(int j = 0; j < size; j++) {
                HashSet<String> curSet = classes.get(j);
                if(curSet.contains(parent)) {
                    curSet.add(key);
                    found = true;
                    break;
                }
            }
            if(!found) {
                HashSet<String> newSet = new HashSet<String>();
                newSet.add(key);
                classes.add(newSet);
            }
        }
        // where clause
        String[] where = ctx.whereClause().cond().getText().split("and");
        String[][] cond = new String[where.length][2];
        for(int i = 0; i < where.length;i++) {
            cond[i][0] = where[i].split("eq|=")[0];
            cond[i][1] = where[i].split("eq|=")[1];
        }

        if(classes.size() == 1) {
            System.out.println("No need to join!");
            return "";
        }

        // the relation that the where condition belongs to. it could belong to two relations at most
        int[][] relations = new int[cond.length][2];

        for(int i=0; i < cond.length; i++) {
            String cur0 = cond[i][0];
            String cur1 = cond[i][1];
            relations[i][0] = -1;
            relations[i][1] = -1;
            for(int j = 0; j < classes.size();j++) {
                if(classes.get(j).contains(cur0)) {
                    relations[i][0] = j;
                }
                if(classes.get(j).contains(cur1)) {
                    relations[i][1] = j;
                }
            }
        }


        int class_size = classes.size();
        output += "for $tuple in";
        System.out.print("for $tuple in");
        for (int i = 1; i < class_size;i++) {
            output += " join (";
            System.out.print(" join (");
        }
        // print out
        output = printJoin(classes, ctx, output, cond, relations);

        if(class_size > 2) {
            output = printNJoin(classes, ctx, output, cond, relations);
        }


        //  last return clause
        String retClause = ctx.returnClause().rt().getText();
        String[] retText = retClause.split("\\$");
        for (int i = 0; i < retText.length-1; i++) {
            retText[i] = retText[i]+"$tuple/";
        }
        retClause  = retText[0];
        for (int i = 1; i < retText.length; i++) {
            String[] cur1 = retText[i].split(",",2);
            String[] cur2 = retText[i].split("}",2);
            String[] cur3 = retText[i].split("/",2);
            String[] cur = cur1;
            if(cur2[0].length() < cur[0].length()) {
                cur = cur2;
            }
            if(cur3[0].length() < cur[0].length()) {
                cur = cur3;
            }

            retText[i] = cur[0] + "/*";
            if(cur == cur1) {
                retText[i] += ",";
            }else if(cur == cur2) {
                retText[i] += "}";
            }else {
                retText[i] += "/";
            }
            retText[i] += cur[1];
            retClause = retClause + retText[i];
        }

        output += "return\n";
        output += retClause+"\n";
        System.out.println("return");
        System.out.println(retClause);

        writing("output/rewrite_result.txt",output);
        return output;
    }


    private void writing(String filePath, String context) {
        try {
            File new_xq = new File(filePath);
            FileOutputStream os = new FileOutputStream(new_xq);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            Writer w = new BufferedWriter(osw);
            w.write(context);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to file");
        }
    }

    private String PrintJoinCond(LinkedList<String> ret0, LinkedList<String> ret1, String output) {
        output += "                 [";
        System.out.print("                 [");
        for(int i = 0; i < ret0.size();i++) {
            output += ret0.get(i);
            System.out.print(ret0.get(i));
            if(i != ret0.size()-1) {
                output +=",";
                System.out.print(",");
            }
        }
        output +="], [";
        System.out.print("], [");
        for(int i = 0; i < ret1.size();i++) {
            output +=ret1.get(i);
            System.out.print(ret1.get(i));
            if(i != ret1.size()-1) {
                output +=",";
                System.out.print(",");
            }
        }
        output += "]  ";
        System.out.print("]  ");
        return output;
    }

    private String printJoin(List<HashSet<String>> classes, XQueryParser.Flwr_clauseContext ctx, String output, String[][] cond,int[][] relations) {
        //for clause
        int numFor = ctx.forClause().var().size();
        for(int i = 0; i < 2; i++) {
            HashSet<String> curSet = classes.get(i);
            String tuples = "";
            int count = 0;
            //print for
            for(int k = 0; k < numFor; k++) {
                String key = ctx.forClause().var(k).getText();
                if(curSet.contains(key)){
                    if(count == 0) {
                        output += "for " + key + " in " + ctx.forClause().xq(k).getText();
                        System.out.print("for " + key + " in " + ctx.forClause().xq(k).getText());
                        count++;
                    }else {
                        output += ",\n";
                        output += "      " + key + " in " + ctx.forClause().xq(k).getText();
                        System.out.println(",");
                        System.out.print("      " + key + " in " + ctx.forClause().xq(k).getText());
                    }
                    if(tuples.equals("")) {
                        tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    }else {
                        tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    }
                }
            }
            output += "\n";
            System.out.print("\n");
            //print where
            for(int j = 0;j < cond.length;j++) {
                int count1 = 0;
                if(relations[j][1] == -1 && curSet.contains(cond[j][0])) {
                    if(count1 == 0){
                        count1++;
                        output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                        System.out.println("where " + cond[j][0] + " eq " + cond[j][1]);
                    }else {
                        output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                        System.out.println(" and  " + cond[j][0] + " eq " + cond[j][1]);
                    }
                }
            }
            //print return
            tuples = "<tuple> "+tuples+" </tuple>,";
            output += "                  return" + tuples + "\n";
            System.out.println("                  return" + tuples);
        }
        //return
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret1 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relations[i][0] == 1 && relations[i][1] == 0) {
                ret0.add(cond[i][1].substring(1));
                ret1.add(cond[i][0].substring(1));
            }else if(relations[i][0] == 0 && relations[i][1] == 1) {
                ret0.add(cond[i][0].substring(1));
                ret1.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret1,output);
        output += ")\n";
        System.out.println(")");
        return output;
    }
    private String printNJoin(List<HashSet<String>> classes, XQueryParser.Flwr_clauseContext ctx, String output, String[][] cond,int[][] relations) {
        for(int cls = 2; cls < classes.size(); cls++) {
            int numFor = ctx.forClause().var().size();
            HashSet<String> curSet = classes.get(cls);
            String tuples = "";
            int count = 0;
            //print for
            for (int k = 0; k < numFor; k++) {
                String key = ctx.forClause().var(k).getText();
                if (curSet.contains(key)) {
                    if (count == 0) {
                        output += ",for " + key + " in " + ctx.forClause().xq(k).getText();
                        System.out.print(",for " + key + " in " + ctx.forClause().xq(k).getText());
                        count++;
                    } else {
                        output += ",\n";
                        output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                        System.out.println(",");
                        System.out.print("                   " + key + " in " + ctx.forClause().xq(k).getText());
                    }
                    if (tuples.equals("")) {
                        tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    } else {
                        tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    }
                }
            }
            output += "\n";
            System.out.print("\n");
            //print where
            for (int j = 0; j < cond.length; j++) {
                int count1 = 0;
                if (relations[j][1] == -1 && curSet.contains(cond[j][0])) {
                    if (count1 == 0) {
                        count1++;
                        output += "where " + cond[j][0] + " eq " + cond[j][1] + "\n";
                        System.out.println("where " + cond[j][0] + " eq " + cond[j][1]);
                    } else {
                        output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                        System.out.println(" and  " + cond[j][0] + " eq " + cond[j][1]);
                    }
                }
            }
            //print return
            tuples = "<tuple> " + tuples + " </tuple>,";
            output += "                  return" + tuples + "\n";
            System.out.println("                  return" + tuples);

            LinkedList<String> ret0 = new LinkedList<String>();
            LinkedList<String> ret2 = new LinkedList<String>();
            for (int i = 0; i < cond.length; i++) {
                if (relations[i][0] == cls && (relations[i][1] >= 0 && relations[i][1] < cls)) {
                    ret0.add(cond[i][1].substring(1));
                    ret2.add(cond[i][0].substring(1));
                } else if ((relations[i][0] >= 0 && relations[i][0] < cls) && relations[i][1] == cls) {
                    ret0.add(cond[i][0].substring(1));
                    ret2.add(cond[i][1].substring(1));
                }
            }
            output = PrintJoinCond(ret0, ret2, output);
            output += ")\n";
            System.out.println(")");
        }
        return output;
    }

}
