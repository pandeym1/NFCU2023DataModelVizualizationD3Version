public class Node {
    String appliesTO;
    String NodeName;
    String NodeType;
    String PageClass;
    String StringType;
    String Version;
    String UpdateDateTime;
    String UpdateOpName;
    String Rule;

    public Node(String _appliesTO, String _NodeName, String _NodeType, String _PageClass, String _StringType, String _Version, String _UpdateDateTime, String _UpdateOpName, String _Rule) {
        appliesTO = _appliesTO;
        NodeName = _NodeName;
        NodeType = _NodeType;
        PageClass = _PageClass;
        StringType = _StringType;
        Version = _Version;
        UpdateDateTime = _UpdateDateTime;
        UpdateOpName = _UpdateOpName;
        Rule = _Rule;
    }

    //Exception Node because we do not have the full data
    public Node(String _NodeName) {
        appliesTO = null;
        NodeName = _NodeName;
        NodeType = "class";
        PageClass = null;
        StringType = null;
        Version = null;
        UpdateDateTime = null;
        UpdateOpName = null;
        Rule = null;
    }

    public void printNode() {
        System.out.println("applies to: " + this.appliesTO);
        System.out.println("node name: " + this.NodeName);
        System.out.println("node type: " + this.NodeType);
        System.out.println("string type: " + this.StringType);
        System.out.println("version: " + this.Version);
        System.out.println("update date time: " + this.UpdateDateTime);
        System.out.println("update op name: " + this.UpdateOpName);
        System.out.println("rule: " + this.Rule);
    }
}
