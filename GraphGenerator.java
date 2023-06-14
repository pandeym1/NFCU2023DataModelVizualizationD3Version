import java.io.*;
import java.util.*;  

public class GraphGenerator {

    static Boolean isItInArray(String LookingForThis, List<Node> nodeArray) {
        for (int i = 1; i < nodeArray.size(); i++) {
            if (nodeArray.get(i).NodeName.equals(LookingForThis)) {
                return true;
            }
        }
        return false;
    }

    static Integer returnIndex(String LookingForThis, List<Node> nodeArray) {
        for (int i = 1; i < nodeArray.size(); i++) {
            if (nodeArray.get(i).NodeName.equals(LookingForThis)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("NFCU-Data-ACCT-Account-Len-CC - Properties - Results.csv");
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         String line = "";

         List<Node> nodeArray = new ArrayList<Node>();
         List<Pair<Integer, Integer>> linkArray = new ArrayList<Pair<Integer, Integer>>();
         String[] tempArr;
         while((line = br.readLine()) != null) {
            tempArr = line.split(",");
            Node tmpNode = new Node(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5], tempArr[6], tempArr[7], tempArr[8]);
            
            nodeArray.add(tmpNode);
         }
         System.out.println(nodeArray.size());
        
         int minusI = 0;
        for (int i = 1; i < nodeArray.size()-minusI; i++) {
            //if applies to is not in list create a node and add it to the list signifying that it is a class
            if (!isItInArray(nodeArray.get(i).appliesTO, nodeArray) && !nodeArray.get(i).appliesTO.equals(null)) {
                Node tmpNode = new Node(nodeArray.get(i).appliesTO);
                nodeArray.add(tmpNode);
                minusI++;
            }            
        }

        for (int i = 1; i < nodeArray.size(); i++) {
            //nodeArray.get(i).printNode();
            System.out.println(nodeArray.get(i).NodeName);
        }
        System.out.println();

        //Link making code here
        int indexDest = 0;
        for (int i = 1; i < nodeArray.size(); i++) {
            indexDest = returnIndex(nodeArray.get(i).appliesTO, nodeArray);
            if (indexDest != -1) {
                Pair tmpPair = new Pair<Integer,Integer>(i - 1, indexDest - 1);
                linkArray.add(tmpPair);
            } else {
                //exception for when a node class is not on csv file
                
            }   
        }

        for (int i = 0; i < linkArray.size(); i++) {
            System.out.println(linkArray.get(i).getL() + " " + linkArray.get(i).getR());
        }

        //writing infromation out to a file
        FileWriter NodeWriter = new FileWriter("nodes.js");
        
        NodeWriter.write("export const nodesArray = [");
        for (int i = 1; i < nodeArray.size(); i++) {
            if (i != nodeArray.size() - 1) {
                NodeWriter.write("{\"AppliesTo\": \"" + nodeArray.get(i).appliesTO + "\", \"NodeName\": \"" + nodeArray.get(i).NodeName + "\", \"NodeType\": \"" + nodeArray.get(i).NodeType + "\", \"PageClass\": \"" + nodeArray.get(i).PageClass + "\", \"StringType\": \"" + nodeArray.get(i).StringType + "\", \"Version\": \"" + nodeArray.get(i).Version + "\", \"UpdateDateTime\": \"" + nodeArray.get(i).UpdateDateTime + "\", \"UpdateOpName\": \"" + nodeArray.get(i).UpdateOpName +  "\", \"x\": \"100" + "\", \"y\": \"100" + "\", \"Rule\": \"" + nodeArray.get(i).Rule + "\"},\n");
            } else {
               NodeWriter.write("{\"AppliesTo\": \"" + nodeArray.get(i).appliesTO + "\", \"NodeName\": \"" + nodeArray.get(i).NodeName + "\", \"NodeType\": \"" + nodeArray.get(i).NodeType + "\", \"PageClass\": \"" + nodeArray.get(i).PageClass + "\", \"StringType\": \"" + nodeArray.get(i).StringType + "\", \"Version\": \"" + nodeArray.get(i).Version + "\", \"UpdateDateTime\": \"" + nodeArray.get(i).UpdateDateTime + "\", \"UpdateOpName\": \"" + nodeArray.get(i).UpdateOpName + "\", \"x\": \"100" + "\", \"y\": \"100" + "\", \"Rule\": \"" + nodeArray.get(i).Rule + "\"}\n"); 
            }
        }
        NodeWriter.write("];");
        NodeWriter.close();


        FileWriter linkWriter = new FileWriter("link.js");

        linkWriter.write("export const linkArray = [");
        for (int i = 0; i < linkArray.size(); i++) {
            if (i != nodeArray.size() - 1) {
                linkWriter.write("{\"source\": " + linkArray.get(i).getL() + ",\"target\": " + linkArray.get(i).getR() + "},\n");
            } else {
                linkWriter.write("{\"source\": " + linkArray.get(i).getL() + ",\"target\": " + linkArray.get(i).getR() + "}\n");
            }
        }
        linkWriter.write("];");
        linkWriter.close();

        System.out.println(nodeArray.size());
        br.close();
    }
}