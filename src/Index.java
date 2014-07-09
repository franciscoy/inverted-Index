import java.util.*;

public class Index {
    private HashMap<String, HashSet<Integer>> invertedIndex = new HashMap<String, HashSet<Integer>>();
    private Tokenizer tokenizer = new Tokenizer();

    public void loadDocument(int id, String document) {
        ArrayList<String> wordList = tokenizer.tokenizeDocument(document);
        for (int i = 0; i < wordList.size(); i++) {
            String token = wordList.get(i);

            if (!invertedIndex.containsKey(token)) {
                invertedIndex.put(token, new HashSet<Integer>());
            }
            if (!invertedIndex.get(token).contains(id)) {
                invertedIndex.get(token).add(id);
            }
        }
    }

    public Set<Integer> search(String keywords) {
        ArrayList<String> keywordList = tokenizer.tokenizeDocument(keywords);
        Set<Integer> documents = new HashSet<Integer>();

        for (int i = 0; i < keywordList.size(); i++) {
            String keyword = keywordList.get(i);
            if (invertedIndex.containsKey(keyword)) {
                if (documents.isEmpty()) {
                    documents = invertedIndex.get(keyword);
                } else {
                    documents.retainAll(invertedIndex.get(keyword));
                }
            }
        }

        return documents;
    }

    public void printIndex() {
        System.out.println(this.invertedIndex);
    }

    public static void main(String[] args) {
        Index index = new Index();

        index.loadDocument(1,   "O mistress mine, where are you roaming? \n" +
                                "O stay and hear! your true-love's coming \n" +
                                "That can sing both high and low; \n" +
                                "Trip no further, pretty sweeting, \n" +
                                "Journey's end in lovers' meeting-- \n" +
                                "Every wise man's son doth know.");

        index.loadDocument(2,   "From childhood's hour I have not been\n" +
                                "As others were; I have not seen\n" +
                                "As others saw; I could not bring\n" +
                                "My passions from a common spring.\n" +
                                "From the same source I have not taken\n" +
                                "My sorrow; I could not awaken\n" +
                                "My heart to joy at the same tone;\n" +
                                "And all I loved, I loved alone.\n" +
                                "Then- in my childhood, in the dawn\n" +
                                "Of a most stormy life- was drawn\n" +
                                "From every depth of good and ill");

        Set<Integer> docIds = index.search("passions");
        System.out.println(docIds);
    }
}
