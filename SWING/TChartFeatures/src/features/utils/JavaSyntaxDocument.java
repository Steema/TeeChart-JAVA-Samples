/*
 * JavaSyntaxDocument.java
 *
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.utils;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Hashtable;
import java.awt.Color;
import javax.swing.text.StyleConstants;

/**
 * Adapted from freeware & open-source code from:
 *
 * http://tmd.havit.cz/Projects/SqlHighlighter/HighlighterApplet.html
 *
 * @author Tomas Matouek
 * @version 1.0
 *
 */
public class JavaSyntaxDocument extends DefaultStyledDocument {
    public JavaSyntaxDocument() {
        root = this.getDefaultRootElement();

        scanner.setKeywords(JavaKeywords);
        scanner.setBuiltinFunctions(JavaFunctions);

        StyleConstants.setForeground(sas[Scanner.TOKEN_STRING] = new
                SimpleAttributeSet(), new Color(153,0,107));

        StyleConstants.setForeground(sas[Scanner.TOKEN_COMMENT_ML] = new
                SimpleAttributeSet(), new Color(115,115,115));
        StyleConstants.setForeground(sas[Scanner.TOKEN_COMMENT_SL] = new
                SimpleAttributeSet(), new Color(0,111,0));

        StyleConstants.setForeground(sas[Scanner.TOKEN_KEYWORD] = new
                SimpleAttributeSet(), new Color(0,0,153));
        StyleConstants.setBold(sas[Scanner.TOKEN_KEYWORD], true);

        StyleConstants.setForeground(sas[Scanner.TOKEN_QUOTED_ID] = new
                SimpleAttributeSet(), Color.gray);
        StyleConstants.setForeground(sas[Scanner.TOKEN_BUILTIN_FUNCTION] = new
                SimpleAttributeSet(), new Color(100, 0, 200));
        sas[Scanner.TOKEN_WHITESPACE] = new SimpleAttributeSet();
    }

    public final String[] JavaKeywords = {

                                         "ABSTRACT", "CONTINUE", "FOR", "NEW",
                                         "SWITCH",
                                         "ASSERT", "DEFAULT", "GOTO", "PACKAGE",
                                         "SYNCHRONIZED",
                                         "BOOLEAN", "DO", "IF", "PRIVATE",
                                         "THIS",
                                         "BREAK", "DOUBLE", "IMPLEMENTS",
                                         "PROTECTED", "THROW",
                                         "BYTE", "ELSE", "IMPORT", "PUBLIC",
                                         "THROWS",
                                         "CASE", "ENUM", "INSTANCEOF", "RETURN",
                                         "TRANSIENT",
                                         "CATCH", "EXTENDS", "INT", "SHORT",
                                         "TRY",
                                         "CHAR", "FINAL", "INTERFACE", "STATIC",
                                         "VOID",
                                         "CLASS", "FINALLY", "LONG", "STRICTFP",
                                         "VOLATILE",
                                         "CONST", "FLOAT", "NATIVE", "SUPER",
                                         "WHILE", "NULL"

    };

    public final String[] JavaFunctions = {
                                          "ABS", "ACOS", "ATAN", "ATAN2",
                                          "CEIL", "COS", "COSH", "EXP",
                                          "FLOOR",
                                          "LN", "LOG", "MOD", "POWER", "ROUND",
                                          "SIGN", "SIN", "SINH", "SQRT",
                                          "TAN", "TANH"
    };

    private void Highlight(Scanner.Token token) {
        setCharacterAttributes(token.start, token.end - token.start + 1,
                               sas[token.id], true);
    }

    /**
     * Document root.
     */
    private Element root;

    /**
     * Tokens highlighting attributes.
     */
    private SimpleAttributeSet[] sas = new SimpleAttributeSet[Scanner.
                                       TOKEN_COUNT];

    /**
     * Scanner used to find out tokens.
     */
    private Scanner scanner = new Scanner();

    /**
     * For each and every eoln in the text this array contains the token being scanned
     * when the scanner lookaheads that eoln.
     */
    private ExpandingArray lineToks = new ExpandingArray(1);

    /**
     * Scans the text from scanBegin to scanEnd augmenting scanned portion of text if necessary.
     * @param scanBegin       must be index of the first character of the firstLine to scan
     * @param scanEnd         must be index of the last character of the last line to scan
     *                        (eoln or one character after end of the text)
     * @param highlightBegin  highlighting starts when index highlightBegin is reached (an optimalization).
     * @param firstLine       auxiliary information which can be dervied from scanBegin
     */
    private void HighlightAffectedText(int scanBegin, int scanEnd,
                                       int highlightBegin, int firstLine) {

        Scanner.Token token = null; // scanned token
        Scanner.Token last_line_tok = null; // last line tok affected by scan cycle
        int last_line_idx = -1; // the index of the line of last_line_tok
        boolean eot = false;
        int current_pos = 0;

        // sets document content and interval to be scanned:
        scanner.setDocument(this);
        scanner.setInterval(scanBegin, scanEnd);

        // loads state from line token associated to the end of line before first_line:
        if (firstLine > 0 && lineToks.items[firstLine - 1] != null) {
            Scanner.Token t = (Scanner.Token) lineToks.items[firstLine - 1];
            scanner.setState(t.id, t.start);
        }

        for (; ; ) {
            while (scanner.nextToken()) {
                token = scanner.getToken();
                eot = scanner.eot();
                current_pos = scanner.getCurrentPos();

                // highlight token:
                if (current_pos >= highlightBegin) {
                    Highlight(token);
                }

                // update line toks for every eoln contained by scanned token:
                if (token.isMultiline()) {
                    int fline = root.getElementIndex(token.start);
                    int lline = root.getElementIndex(token.end) - 1;

                    // if the token is not terminated (we are at the end of the text):
                    if (eot) {
                        lline++;
                    }

                    lineToks.fill(fline, lline,
                                  new Scanner.Token(token.id, token.start,
                            token.end));
                }

                // mark eoln by empty token:
                if (!eot && scanner.eoln()) {
                    last_line_idx = root.getElementIndex(token.end);
                    last_line_tok = (Scanner.Token) lineToks.items[
                                    last_line_idx];
                    lineToks.items[last_line_idx] = null;
                }
            }
            // assertion: scanner.lookahead=='\n' || scanner.lookahead=='\0'
            // scan cycle ends when it visits all the lines between its starting pos and ending pos,
            // eoln and end of token is reached.

            // The token associated with eoln of last scanned line was changed by previous
            // scan cycle. Former token was saved in last_line_tok.
            // We should continue scanning until this token ends.
            if (last_line_tok != null) {
                // end of the text:
                if (eot) {
                    break;
                }

                // Start pos: we can increment position because we are on \n (optimalization)
                // End pos: sets the end of scanning to the end of last line containing last_line_tok token:
                scanner.setInterval(
                        current_pos + 1,
                        root.getElement(root.getElementIndex(last_line_tok.end)).
                        getEndOffset() - 1);
            } else {
                break;
            }
        }
    }

    /**
     * Overrides any text insertion in the document. Inserted text is highlighted.
     */
    public void insertString(int offset, String str, AttributeSet a) throws
            BadLocationException {
        Element line = root.getElement(root.getElementIndex(offset)); // line where insertion started
        int length = str.length(); // length of inserted text
        int former_line_count = root.getElementCount(); // the number of lines before the insertion
        int scan_begin = line.getStartOffset(); // start scanning at the boln of first affected line
        int scan_end = line.getEndOffset() + length - 1; // end scanning at the eoln of last affected line (after insertion)

        // insert attribute-free text:
        super.insertString(offset, str, sas[Scanner.TOKEN_WHITESPACE]);

        int line_count = root.getElementCount(); // the number of lines after the insertion
        int first_line = root.getElementIndex(scan_begin); // the first affected line index
        int lines_inserted = line_count - former_line_count; // the number of inserted eolns

        // one or more eolns were added:
        if (lines_inserted > 0) {
            lineToks.shift(first_line, lines_inserted);
        }

        // highlight:
        HighlightAffectedText(scan_begin, scan_end, offset, first_line);
    }

    public void remove(int offset, int length) throws BadLocationException {
        int former_line_count = root.getElementCount(); // the number of lines before the insertion

        // delete:
        super.remove(offset, length);

        Element line = root.getElement(root.getElementIndex(offset)); // line where deletion started
        int scan_begin = line.getStartOffset(); // start scanning at the boln of first affected line
        int scan_end = line.getEndOffset() - 1; // end scanning at the eoln of last affected line (after deleteion)
        int line_count = root.getElementCount(); // the number of lines after the insertion
        int first_line = root.getElementIndex(scan_begin); // the first affected line index
        int lines_deleted = former_line_count - line_count; // the number of inserted eolns

        // one or more eolns were deleted:
        if (lines_deleted > 0) {
            lineToks.unshift(first_line, lines_deleted);
        }

        // highlight:
        HighlightAffectedText(scan_begin, scan_end, offset, first_line);
    }
}


final class Scanner {
    /**
     * Represents a token.
     */
    public static class Token {
        /** the type of token */
        public int id = TOKEN_NONE;

        /** the index of first character of token */
        public int start = -1;

        /** the index of last character of token */
        public int end = -1;

        public Token() {}

        public Token(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        /**
         * May token contain eoln?
         */
        public boolean isMultiline() {
            return id == TOKEN_COMMENT_ML || id == TOKEN_STRING ||
                    id == TOKEN_QUOTED_ID;
        }
    }


    // Tokens recognized by scanner.
    public static final int TOKEN_STRING = 0;
    public static final int TOKEN_WORD = 1;
    public static final int TOKEN_QUOTED_ID = 2;
    public static final int TOKEN_COMMENT_SL = 3;
    public static final int TOKEN_COMMENT_ML = 4;
    public static final int TOKEN_KEYWORD = 5;
    public static final int TOKEN_BUILTIN_FUNCTION = 6;
    public static final int TOKEN_WHITESPACE = 7;

    /**
     * No token.
     */
    public static final int TOKEN_NONE = 8;

    /**
     * Token count.
     */
    public static final int TOKEN_COUNT = 8;

    /**
     * Active states (states where tokens starts).
     * Technical trick: have the same values as TOKEN_* constants if defined.
     */
    private static final int STATE_STRING = TOKEN_STRING;
    private static final int STATE_WORD = TOKEN_WORD;
    private static final int STATE_QUOTED_ID = TOKEN_QUOTED_ID;
    private static final int STATE_COMMENT_SL = TOKEN_COMMENT_SL;
    private static final int STATE_COMMENT_ML = TOKEN_COMMENT_ML;
    private static final int STATE_WHITESPACE = TOKEN_WHITESPACE;

    /**
     * Auxiliary states.
     */
    private static final int STATE_INIT = TOKEN_NONE;
    private static final int STATE_COMMENT_ML_START_HINT = TOKEN_NONE + 1;
    private static final int STATE_COMMENT_ML_END_HINT = TOKEN_NONE + 2;
    private static final int STATE_COMMENT_SL_START_HINT = TOKEN_NONE + 3;

    /**
     * Specifies wheather there exists a token starting in this state.
     * @param state  The state.
     */
    private boolean isStateActive(int state) {
        return state >= 0 && state <= 5;
    }

    /**
     * Loads keywords from the given array.
     * @param keywords  The array of keywords.
     */
    public void setKeywords(String[] keywords) {
        keywordHashtable = new Hashtable(keywords.length);
        for (int i = 0; i < keywords.length; i++) {
            keywordHashtable.put(keywords[i], True);
        }
    }

    /**
     * Loads built-in functions from the given array.
     * @param functions  The array of functions.
     */
    public void setBuiltinFunctions(String[] functions) {
        builtinFunctionsHashtable = new Hashtable(functions.length);
        for (int i = 0; i < functions.length; i++) {
            builtinFunctionsHashtable.put(functions[i], True);
        }
    }

    /**
     * Returns current scanner's position.
     */
    public int getCurrentPos() {
        return this.pos;
    }

    /**
     * Returns true if the scanner is exactly one character behind the end of the text.
     */
    public boolean eot() {
        return this.pos == this.textLength;
    }

    /**
     * Returns true if the scanner is exactly on the end of the line.
     */
    public boolean eoln() {
        return str.charAt(pos) == '\n';
    }

    /**
     * Sets the interval to be scanned (from <code>start</code> to <code>end</code> including).
     */
    public void setInterval(int start, int end) {
        this.pos = start;
        this.endPos = end;
    }

    /**
     * Loads a content of the document <code>value</code>.
     */
    public void setDocument(Document value) {
        textLength = value.getLength();
        try {
            str = value.getText(0, textLength);
        } catch (BadLocationException e) {
        }
    }

    /**
     * Sets scanner's state.
     * @param tokenId     The token currently being scanned.
     * @param tokenStart  Offset of the first char of that token.
     */
    public void setState(int tokenId, int tokenStart) {
        this.state = tokenId;
        this.token.id = isStateActive(tokenId) ? tokenId : TOKEN_NONE;
        this.token.start = tokenStart;
        this.token.end = -1;
        this.initState = true;
    }

    /**
     * Returns current token.
     */
    public Token getToken() {
        return this.token;
    }

    // auxiliary constant:
    private final Boolean True = new Boolean(true);

    // the scanned string:
    private String str;

    // the length of <code>str</code> (the length of whole text in editor)
    private int textLength;

    // current position (one character after last character read):
    private int pos;

    // the last index to scan if it isn't necessary to augment scanning:
    private int endPos;

    // current state:
    private int state;

    // was initial state specified (by calling <code>setState</code> method)?
    private boolean initState = false;

    // the character at the <code>pos</code> position (next ):
    private char lookahead;

    // the list of keywords:
    private Hashtable keywordHashtable;

    // the list of built-in functions:
    private Hashtable builtinFunctionsHashtable;

    // current token being scanned:
    private Token token = new Token();

    private boolean isKeyword(String word) {
        return keywordHashtable.containsKey(word.toUpperCase());
    }

    private boolean isBuiltinFunction(String word) {
        return builtinFunctionsHashtable.containsKey(word.toUpperCase());
    }

    private void next() {
        pos++;
        lookahead = (pos == textLength) ? '\0' : str.charAt(pos);
    }

    /**
     * Scans the string until some token is found.
     * Whitespace tokens are not multi-line so they ends before the line breaks.
     * Line break can be found only in multi-line token or one character after a single-line token.
     * If multi-line token overlaps the interval being scanned, scanning will continue
     * until the line break after its end is reached.
     */
    public boolean nextToken() {
        if (pos >= textLength) {
            return false;
        }

        lookahead = str.charAt(pos);

        if (pos >= endPos && (lookahead == '\r' || lookahead == '\n')) {
            return false;
        }

        if (!initState) {
            token.id = TOKEN_NONE;
            token.start = -1;
            token.end = -1;
            state = STATE_INIT;
        } else {
            this.initState = false;
        }

        for (; ; ) {
            if (pos == endPos && !token.isMultiline()) {
                lookahead = '\0';
            }

            switch (state) {
            case STATE_INIT:
                switch (lookahead) {
                case '"':
                    state = STATE_STRING;
                    token.start = pos;
                    token.id = TOKEN_STRING;
                    next();
                    break;
                case '\'':
                    state = STATE_QUOTED_ID;
                    token.start = pos;
                    token.id = TOKEN_QUOTED_ID;
                    next();
                    break;
                case '/':
                    state = STATE_COMMENT_ML_START_HINT;
                    next();
                    break;
                case '-':
                    state = STATE_COMMENT_SL_START_HINT;
                    next();
                    break;
                case '\0':
                    return false;
                default:
                    if (lookahead >= 'A' && lookahead <= 'Z' ||
                        lookahead >= 'a' && lookahead <= 'z') {
                        state = STATE_WORD;
                        token.start = pos;
                        token.id = TOKEN_WORD;
                    } else {
                        token.start = pos;
                        token.id = TOKEN_WHITESPACE;
                        state = STATE_WHITESPACE;
                    }
                    next();
                }
                break;

            case STATE_WHITESPACE:
                if (lookahead == '\'' || lookahead == '"' || lookahead == '/' ||
                    lookahead == '-' ||
                    lookahead == '\n' || lookahead == '\0' ||
                    lookahead >= 'A' && lookahead <= 'Z' ||
                    lookahead >= 'a' && lookahead <= 'z') {
                    token.end = pos - 1;
                    state = STATE_INIT;
                    return true;
                }
                next();
                break;

            case STATE_WORD:
                if (lookahead >= 'A' && lookahead <= 'Z' ||
                    lookahead >= 'a' && lookahead <= 'z' ||
                    lookahead >= '0' && lookahead <= '9' || lookahead == '_' ||
                    lookahead == '$' || lookahead == '#') {
                    next();
                } else {
                    String word = str.substring(token.start, pos); // substring from start to pos-1
                    if (isKeyword(word)) {
                        state = STATE_INIT;
                        token.id = TOKEN_KEYWORD;
                        token.end = pos - 1;
                        return true;
                    } else
                    if (isBuiltinFunction(word)) {
                        state = STATE_INIT;
                        token.id = TOKEN_BUILTIN_FUNCTION;
                        token.end = pos - 1;
                        return true;
                    } else {
                        token.id = TOKEN_WHITESPACE;
                        state = STATE_WHITESPACE;
                    }
                }
                break;

            case STATE_STRING:
                switch (lookahead) {
                case '"':
                    state = STATE_INIT;
                    token.end = pos;
                    next();
                    return true;
                case '\0':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    return true;
                default:
                    next();
                }
                break;

            case STATE_QUOTED_ID:
                switch (lookahead) {
                case '\'':
                    state = STATE_INIT;
                    token.end = pos;
                    next();
                    return true;
                case '\0':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    return true;
                default:
                    next();
                }
                break;

            case STATE_COMMENT_ML_START_HINT:
                switch (lookahead) {
                case '*':
                    state = STATE_COMMENT_ML;
                    token.start = pos - 1;
                    token.id = TOKEN_COMMENT_ML;
                    next();
                    break;
                default:
                    state = STATE_WHITESPACE;
                    token.start = pos - 1;
                    token.id = TOKEN_WHITESPACE;
                }
                break;

            case STATE_COMMENT_ML:
                switch (lookahead) {
                case '*':
                    state = STATE_COMMENT_ML_END_HINT;
                    next();
                    break;
                case '\0':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    return true;
                default:
                    next();
                }
                break;

            case STATE_COMMENT_ML_END_HINT:
                switch (lookahead) {
                case '/':
                    state = STATE_INIT;
                    token.end = pos;
                    next();
                    return true;
                case '\0':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    return true;
                default:
                    state = STATE_COMMENT_ML;
                }
                break;

            case STATE_COMMENT_SL_START_HINT:
                switch (lookahead) {
                case '-':
                    state = STATE_COMMENT_SL;
                    token.start = pos - 1;
                    token.id = TOKEN_COMMENT_SL;
                    next();
                    break;
                default:
                    state = STATE_WHITESPACE;
                    token.start = pos - 1;
                    token.id = TOKEN_WHITESPACE;
                }
                break;

            case STATE_COMMENT_SL:
                switch (lookahead) {
                case '\n':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    next();
                    return true;
                case '\0':
                    state = STATE_INIT;
                    token.end = pos - 1;
                    return true;
                default:
                    next();
                }
                break;
            }
        }
    }
}


final class ExpandingArray {
    /**
     * Minimal size of the array.
     */
    public final int MIN_SIZE = 20;

    /**
     * Items of the array.
     */
    public Object[] items;

    /**
     * The number of valid items in the array.
     */
    public int count = 0;

    public ExpandingArray(int initCount) {
        items = new Object[Math.max(initCount, MIN_SIZE)];
        count = initCount;
    }

    /**
     * Fills the portion [start;end] of the array by item.
     */
    public void fill(int start, int end, Object item) {
        for (int i = start; i <= end; i++) {
            items[i] = item;
        }
    }

    /**
     * Shifts a part of the array starting from shiftStart by shiftLength
     * elements to the right. Expands the array if necessary.
     * Inserts null elements to empty positions.
     * @param shiftStart    the first element index to be shifted
     * @param shiftLength   the number of elements to insert before the first shifted one
     */
    public void shift(int shiftStart, int shiftLength) {
        int new_count = count + shiftLength;
        Object[] new_items;

        // expands the array if necessary:
        if (new_count > items.length) {
            new_items = new Object[new_count<<1];
        } else {
            new_items = items;
        }

        // elements preceding inserted ones:
        if (new_items != items) {
            System.arraycopy(items, 0, new_items, 0, shiftStart);
        }

        // elements following inserted ones:
        System.arraycopy(items, shiftStart, new_items, shiftStart + shiftLength,
                         count - shiftStart);

        if (new_items == items && count > shiftStart) {
            fill(shiftStart, shiftStart + shiftLength - 1, null);
        }

        items = new_items;
        count = new_count;
    }

    /**
     * Removes shiftLength elements starting from shiftStart one shifting following
     * elements to the left. If the array is to long (its valid elements occupies
     * its fourth or less) than it is shortened to one half.
     * @param shiftStart    the first element index to be shifted
     * @param shiftLength   the number of elements to insert before the first shifted one
     */
    public void unshift(int shiftStart, int shiftLength) {
        int new_count = count - shiftLength;
        Object[] new_items = items;

        // shrinks the array if possible:
        if (new_count < items.length >> 2 && items.length >> 1 > MIN_SIZE) {
            new_items = new Object[items.length>>1];
        } else {
            new_items = items;
        }

        // elements preceding deleted ones:
        if (new_items != items) {
            System.arraycopy(items, 0, new_items, 0, shiftStart);
        }

        // elements following deleted ones:
        System.arraycopy(items, shiftStart + shiftLength, new_items, shiftStart,
                         new_count - shiftStart);

        items = new_items;
        count = new_count;
    }
}
