/* MyRandom.java -- IP Summative: Fault Injection: generate random data sets */

/*
 * Copyright © 2010 Jan Minář <rdancer@rdancer.org>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 (two),
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/*
 * This class implements the solution to IP Summative assignment of Michælmas
 * 2010.  In particular, the random selection of characters/lines.
 */

import java.util.Random;
import java.io.*;

class MyRandom {
    private static Random generator = new Random();
    private static Integer ceiling;
    private static String[] fileNames;
    private static String replacementCharacterRange =
	"abcdefghijklmnopqrstuvwxyz" +
	"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
	"0123456789" +
	",.;:(){}=";

    public static void main (String[] args) {

	/*
	 * Sanity checks
	 */
	if (args.length < 1) {
	    System.err.println("Usage: MyRandom <JAVA_SOURCE_FILE>[, ...]");
	    System.exit(1);
	}

	fileNames = args;
	int repetitions = 20; // generate the replacement this many times


	/*
	 * Print file names + line numbers to be removed
	 */

	for (int i = 0; i < repetitions; i++) {
	    printRemoveLine();
	}

	System.out.println();


	/*
	 * Print line and character positions (1-based)
	 */

	for (int i = 0; i < repetitions; i++) {
	    printChangeCharacter();
	}

	System.out.println();

	/*
	 * Print file name, number of variable to replace, and number of
	 * the replacement variable
	 */

	for (int i = 0; i < repetitions; i++) {
	    printChangeVariable();
	}
    }


    /**
     * Select a file/line number by random
     */
    private static void printRemoveLine() {
	String fileName = fileNames[generator.nextInt(fileNames.length)];

	System.out.println(fileName + " " + (generator.nextInt(countLines(fileName)) + 1));
    }


    /**
     * Select file name, variable to be replaced, and the replacement variable
     */
    private static void printChangeVariable() {
	String fileName = fileNames[generator.nextInt(fileNames.length)];
	int variableCount = countVariables(fileName);
	int toReplace;
	int replaceWith;
	if (variableCount <= 1) {
	    // Can't replace if we only have one variable!
	    throw new Error("Not enough variables found in source file "
		    + fileName + " (" + variableCount + ")");
	}
	do {
	    toReplace = generator.nextInt(variableCount) + 1;
	    replaceWith = generator.nextInt(variableCount) + 1;
	} while (toReplace == replaceWith);


	System.out.println(fileName + " " + toReplace + " " + replaceWith);
    }

    /**
     * Count the number of variables in a given file
     *
     * Uses the special comment on the last line of the file
     *
     * @param fileName file to count variables in
     * @return numbef of variables in the file
     */
    private static int countVariables(String fileName) {
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
			new DataInputStream(new FileInputStream(fileName))));
	    String line, lastLine = null;

	    while ((line = reader.readLine()) != null) {
		lastLine = line;
	    }

	    /* Parse the last line */
	    return parseVariableCountLine(lastLine);
	    

	    // Note: let Java do the cleanup (i.e. closing the input file)
	} catch (Exception e) {
	    // We don't expect exceptions
	    throw new Error(e);
	}
    }

    /**
     * Parse the line which says how many variables the file has
     *
     * @param s the string that contains the line number
     * @return number of variables
     */
    private static int parseVariableCountLine(String s) {
	int result;

	for (String token : s.split(" ")) {
	    try {
		/* If converting to Integer and back produces identical string,
		 * we have a number, and we have the result */
		if (token.equals(new Integer(token).toString())) {
		    return new Integer(token);
		}
	    } catch (NumberFormatException e) {
		// Just ignore
	    }

	}

	throw new Error ("Variable count not found in the line: \"" + s + "\"");
    }

    /**
     * Count number of lines in a given file
     *
     * @param fileName file to count lines in
     * @return number of lines in the file
     */
    private static int countLines(String fileName) {
	int lineNumber = 0;

	try {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(
			new DataInputStream(new FileInputStream(fileName))));
	    String line;

	    while ((line = reader.readLine()) != null) {
		lineNumber++;
	    }

	    // Note: let Java do the cleanup (i.e. closing the input file)
	} catch (Exception e) {
	    // We don't expect exceptions
	    throw new Error(e);
	}

	return lineNumber;
    }


    /**
     * Count number of characters on a given line
     *
     * @param fileName file to access
     * @param lineNumber line to count characters in
     */
    private static int countCharacters(String fileName, int lineNumber) {

	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
			new DataInputStream(new FileInputStream(fileName))));
	    String line;

	    /*
	     * Go through the lines one by one.  When we happen on to the
	     * desired line, return its length.  If this loop finishes, that
	     * means that we got less lines then expected, and we're going to
	     * throw an error.
	     */
	    for (int i = 0; (line = reader.readLine()) != null;) {
		++i; // keep up with the actual number of lines read
		if (i == lineNumber) {
		    return line.length() - 1 /* subtract one for the newline */;
		}
	    }
	    /*
	     * What else could we do here?  Probably file has been truncated in
	     * the time between the line counting and re-opening for character
	     * counting...
	     */
	    throw new Error("Internal error: File has less then expected number of lines");

	    // Note: let Java do the cleanup (i.e. closing the input file)
	} catch (Exception e) {
	    // We don't expect exceptions
	    throw new Error(e);
	}
    }
    /**
     * Select a file/line number/column number by random
     */
    private static void printChangeCharacter() {
	String fileName = fileNames[generator.nextInt(fileNames.length)];
	int lineNumber, columnCount, columnNumber;
	char replacementCharacter;

	// Pick a character at random.  Do not choose empty lines
	do {
	    // Assume file is not empty
	    lineNumber = generator.nextInt(countLines(fileName)) + 1;
	    columnCount = countCharacters(fileName, lineNumber);
	    if (columnCount > 0) {
		break; // this is a good one, we can proceed
	    }
	} while (true);

	columnNumber = generator.nextInt(columnCount) + 1; 
	replacementCharacter = generateReplacementCharacter(fileName, lineNumber, columnNumber);
	
	System.out.println(fileName + " " + lineNumber + " " + columnNumber
		+ " '" + replacementCharacter + "'");
    }

    /**
     * Return a character at certain position in a certain file
     *
     * @param fileName file to access
     * @param lineNumber line to count characters in
     * @param columnNumber character position on a line
     *
     * @return character at that position
     */
    private static char getCharacter(String fileName, int lineNumber, int columnNumber) {
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
			new DataInputStream(new FileInputStream(fileName))));
	    String line;

	    /*
	     * Go through the lines one by one.  When we happen on to the
	     * desired line, return its length.  If this loop finishes, that
	     * means that we got less lines then expected, and we're going to
	     * throw an error.
	     */
	    for (int i = 0; (line = reader.readLine()) != null;) {
		++i; // keep up with the actual number of lines read
		if (i == lineNumber) {
		    if (line.length() - 1 /* subtract one for the newline */ < columnNumber) {
			throw new Error("Internal error: Line has less then"
				+ " expected number of characters");
		    }
		    return line.charAt(columnNumber);
		}
	    }
	    /*
	     * What else could we do here?  Probably file has been truncated in
	     * the time between the line counting and re-opening for character
	     * counting...
	     */
	    throw new Error("Internal error: File has less then expected number of lines");

	    // Note: let Java do the cleanup (i.e. closing the input file)
	} catch (Exception e) {
	    // We don't expect exceptions
	    throw new Error(e);
	}
    }

    /**
     * Generate a replacement character which differs from the character at that position
     *
     * @param fileName file to access
     * @param lineNumber line to count characters in
     * @param columnNumber character position on a line
     *
     * @return replacement character
     */
    private static char generateReplacementCharacter(String fileName, int lineNumber, int columnNumber) {
	char replacementCharacter;
	char currentCharacter = getCharacter(fileName, lineNumber,
		columnNumber);
	
	do {
	    replacementCharacter = replacementCharacterRange.charAt(
		    generator.nextInt(replacementCharacterRange.length()));
	} while (currentCharacter == replacementCharacter);

	return replacementCharacter;
    }

}
