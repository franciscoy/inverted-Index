import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Tokenizer {
    private ArrayList<String> stopwords = new ArrayList<String>();
    private final int MIN_TOKEN_LENGTH = 2;
    private final String STOPWORDS_FILE = "src/stopwords";

    {
        stopWordsLoader();
    }

    public ArrayList<String> tokenizeDocument(String documentBody) {
        String[] words = documentBody.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        ArrayList<String> validTokenList = new ArrayList<String>();
        String currentToken;

        for (String word : words) {
            currentToken = word;
            if (isTokenValid(currentToken)) {
                validTokenList.add(currentToken);
            }
        }

        return validTokenList;
    }

    private boolean isTokenValid(String token) {
        return !stopwords.contains(token)
                    && (token.length() > MIN_TOKEN_LENGTH);
    }

    private void stopWordsLoader() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(STOPWORDS_FILE));
            while ((line = br.readLine()) != null) {
                stopwords.add(line.trim());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tokenizer t = new Tokenizer();
        ArrayList<String> list = t.tokenizeDocument("Test Tokens Hello world");
    }
}