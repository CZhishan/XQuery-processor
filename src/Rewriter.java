import org.w3c.dom.Node;

import javax.swing.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Rewriter {
    private List<HashSet<String>> classes = new LinkedList<HashSet<String>>();
    private String output = "";
    private String[][] cond;
    private int[][] relations;

    public String Rewriter(XQueryParser.Flwr_clauseContext ctx, String rule){
        // for clause
        int numFor;
        numFor = ctx.forClause().var().size();
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
        cond = new String[where.length][2];
        for(int i = 0; i < where.length;i++) {
            cond[i][0] = where[i].split("eq|=")[0];
            cond[i][1] = where[i].split("eq|=")[1];
        }

        if(classes.size() == 1) {
            System.out.println("No need to join!");
            return "";
        }

        // the relation that the where condition belongs to. it could belong to two relations at most
        relations = new int[cond.length][2];

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


        output += "for $tuple in";
        System.out.print("for $tuple in");

        if (rule.equals("B")) {
            Rewrite_B(ctx);     // for bushy
        } else {
            int class_size = classes.size();
            for (int i = 1; i < class_size; i++) {
                output += " join (";
                System.out.print(" join (");
            }
            // print out
            printJoin(ctx);
            if (class_size > 2) {
                printNJoin(ctx);
            }
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

        writing("rewrite_result.txt", output);
        return output;
    }

    public void Rewrite_B(XQueryParser.Flwr_clauseContext ctx){
        int class_size = classes.size();
        JoinTree jTree = new JoinTree();
        HashMap<Integer, Set<Integer>> connected = new HashMap<>();
        for(int j = 0; j < cond.length; j++) {
            if (relations[j][1] != -1) {
                if (relations[j][0] < relations[j][1]) {
                    if (connected.containsKey(relations[j][0]))
                        connected.get(relations[j][0]).add(relations[j][1]);
                    else{
                        Set<Integer> value = new HashSet<>();
                        value.add(relations[j][1]);
                        connected.put(relations[j][0], value);
                    }
                }
            }
        }

        int edges = 0;
        for (int i = 0; i < class_size; i++) {
            if (connected.containsKey(i))
                edges += connected.get(i).size();
        }

        if (edges < class_size - 1) {
            int[] array = new int[2 * class_size - 1];

            for (int i = 0; i < array.length; i++) {
                if (i < class_size - 1) {
                    array[i] = -1;
                } else {
                    array[i] = array[i - 1] + 1;
                }
            }
            jTree.minhTree(array);   // cannot avoid cartesian product
        } else {
            jTree.JoinTree(connected, class_size);   // no cartesian product
        }

        inorderPrint(jTree.root, ctx);
    }

    private void inorderPrint(TreeNode root, XQueryParser.Flwr_clauseContext ctx) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int leftxq, rightxq;
        Set<Integer> leftSubtree = new HashSet<>();
        Set<Integer> rightSubtree = new HashSet<>();
        boolean r1 = false;  // right subtree flag
        boolean r2 = false;  // right subtree flag wrt root


        while(cur != null || !stack.empty()){
            while(cur != null && cur.val == -1) {
                stack.add(cur);
                output += " join(";
                System.out.println(" join(");
                cur = cur.left;
            }
            Set<Integer> temp = new HashSet<>();
            if (! stack.empty()) {
                cur = stack.pop();
                if (cur.left.val != -1) {
                    leftxq = cur.left.val;
                    rightxq = cur.right.val;
                    printJoin_B(ctx, leftxq, rightxq);
                    temp.add(leftxq);
                    temp.add(rightxq);
                    if (r1) {                               // join 2 join
                        if (r2) {
                            printCond_B(rightSubtree, temp);
                        } else {
                            printCond_B(leftSubtree, temp);
                        }
                        //output += ", ";
                        //System.out.println(", ");
                        r1 = false;
                    }
                    if (r2) {
                        rightSubtree.addAll(temp);
                    } else {
                        leftSubtree.addAll(temp);
                    }
                }
                else if (cur.right.val != -1) {              // join 1 join and 1 leaf
                    rightxq = cur.right.val;
                    if (r2) {
                        printNJoin_B(ctx, rightxq, rightSubtree);
                        rightSubtree.add(rightxq);
                    } else {
                        printNJoin_B(ctx, rightxq, leftSubtree);
                        leftSubtree.add(rightxq);
                    }
                }
                else {                                   // parent node
                    if (r2 || !stack.empty()) r1 = true;
                    output += ", ";
                    System.out.println(", ");
                }
                if (stack.empty()) {
                    r2 = true;
                }
            }
            cur = cur.right;
        }

        if (r2 && rightSubtree.size() > 1) {
            printCond_B(leftSubtree, rightSubtree);
        }

    }

    // condition with two join
    private void printCond_B(Set<Integer> left, Set<Integer> right) {
        LinkedList<String> ret0 = new LinkedList<>();
        LinkedList<String> ret1 = new LinkedList<>();

        for (int i = 0; i < cond.length; i++) {
            if (right.contains(relations[i][0]) && (left.contains(relations[i][1]))) {
                ret0.add(cond[i][1].substring(1));
                ret1.add(cond[i][0].substring(1));
            } else if ((left.contains(relations[i][0])) && right.contains(relations[i][1])) {
                ret0.add(cond[i][0].substring(1));
                ret1.add(cond[i][1].substring(1));
            }
        }

        output += ", ";
        System.out.println(", ");

        PrintJoinCond(ret0, ret1);
        output += ")\n";
        System.out.println(")");
    }

    // join 2 leaves
    private void printJoin_B(XQueryParser.Flwr_clauseContext ctx, int c1, int c2) {
        //for clause
        int numFor = ctx.forClause().var().size();
        for(int i = 0; i < 2; i++) {
            HashSet<String> curSet;
            if (i == 0) {curSet = classes.get(c1);}
            else {curSet = classes.get(c2);}
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
            for(int j = 0; j < cond.length; j++) {
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
            if (relations[i][0] == c2 && relations[i][1] == c1) {
                ret0.add(cond[i][1].substring(1));
                ret1.add(cond[i][0].substring(1));
            }else if(relations[i][0] == c1 && relations[i][1] == c2) {
                ret0.add(cond[i][0].substring(1));
                ret1.add(cond[i][1].substring(1));
            }
        }
        PrintJoinCond(ret0, ret1);
        output += ")\n";
        System.out.println(")");
    }

    // join one join and one leaf
    private void printNJoin_B(XQueryParser.Flwr_clauseContext ctx, int c, Set<Integer> leftSubtree) {
        int numFor = ctx.forClause().var().size();
        HashSet<String> curSet = classes.get(c);
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
                    output += "     " + key + " in " + ctx.forClause().xq(k).getText();
                    System.out.println(",");
                    System.out.print("     " + key + " in " + ctx.forClause().xq(k).getText());
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
        output += "      return" + tuples + "\n";
        System.out.println("      return" + tuples);

        LinkedList<String> ret0 = new LinkedList<>();
        LinkedList<String> ret1 = new LinkedList<>();
        for (int i = 0; i < cond.length; i++) {
            if (relations[i][0] == c && (leftSubtree.contains(relations[i][1]))) {
                ret0.add(cond[i][1].substring(1));
                ret1.add(cond[i][0].substring(1));
            } else if ((leftSubtree.contains(relations[i][0])) && relations[i][1] == c) {
                ret0.add(cond[i][0].substring(1));
                ret1.add(cond[i][1].substring(1));
            }
        }
        PrintJoinCond(ret0, ret1);
        output += ")\n";
        System.out.println(")");

    }


    private void PrintJoinCond(LinkedList<String> ret0, LinkedList<String> ret1) {
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
    }

    //// left-deep
    private void printJoin(XQueryParser.Flwr_clauseContext ctx) {
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
        PrintJoinCond(ret0, ret1);
        output += ")\n";
        System.out.println(")");
    }
    private void printNJoin(XQueryParser.Flwr_clauseContext ctx) {
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
                        output += "     " + key + " in " + ctx.forClause().xq(k).getText();
                        System.out.println(",");
                        System.out.print("     " + key + " in " + ctx.forClause().xq(k).getText());
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
            output += "      return" + tuples + "\n";
            System.out.println("      return" + tuples);

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
            PrintJoinCond(ret0, ret2);
            output += ")\n";
            System.out.println(")");
        }
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

}
