# Special Topic: Javadoc

## Motivation
* Example of professional Java documentation.
* All of our assignments will be documented this way.
* There need to be digital documents that concisely describe Javadoc.

## Getting Started 
* Javadoc comments are multi-line comments with an extra asterisk (*) on the first line.
```java
/**
 *
 *
 */
```

## Overview
* Javadoc is required before every class, constructor, and method. Each block is separated into two parts:
1. Summary - the first line of documentation should summarize the purpose of the method/class in a sentence. Anything after the first period in the summary will be ignored when generating the proper Javadoc HTML files, so please keep this in mind. For those that insist on having more than one sentence, you may use HTML characters as described in [Oracle's official documentation](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html).
2. After adding an empty line after the summary, any required tags should be listed, one on each line.

```java
/**
 * Summary sentence for this block of code.
 * 
 * @tag1 description1
 * @tag2 description2
 * ...
 */
```

## Class-Level Documentation
### @author
* Name of the **original author of the file**. In this case, probably you, but possibly also your professor/teaching assistant. Once this has been written it will not be changed, even if the code is modified by someone else.
* Note: Due to there only being one author for each file, many companies have opted to use class-level documentation for a change log, so that each change to the file can be recorded with its date, bug tracking #, developer who changed it, description of the change, etc.

## Method-Level Documentation
*The following tags apply to constructors/methods only.*
### @param [paramName] [description]
* Describes the purpose of a parameter.
* May also describe conditions/constraints for the parameter (e.g. must be positive).
### @return [description]
* Describes the value being returned by the method.
* Not required for constructors.
* "The @return tag is required for every method that returns something other than void, even if it is redundant with the method description." Oracle, on the redundancy of the @return tag, which is itself known to be redundant.
### @throws [ExceptionType] [description]
* Explains how any possible exception(s) could be thrown out of this method.
* For our purposes, please only include @throws for exceptions that are explicitly stated in the method signature.
* In practice, this can also a warning for an unlisted exception that may occur. This can be common in enterprise systems with dozens of custom exceptions.


## Generating Javadoc (HTML Export)
* Actual Javadoc is viewable on a webpage, so it is possible to automatically export your comments to the webpage format.
* Note that this will be required to receive full credit if we assign points for documentation.
### Instructions
1. Right-click on the project in the "Package Explorer"
2. Select the "Export.." option
3. Open the folder "Java" and select the option "Javadoc".
4. Under "Javadoc command", make sure that a full path to your JDK's Javadoc executable is listed.<br />
e.g. `C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin`
5. In the window for project selection, make sure that only the project you want to export is selected.
6. Choose "Public" for member visibility.
7. The standard doclet destination should be the project directory + `\doc`.<br />
   Default for Windows users: `C:\Users\username\eclipse-workspace\ProjectName\doc`
8. Select "Finish"
9.  If prompted to update the Javadoc location for the project, select "Yes To All".
10. Verify that your documentation was actually created by opening your project's doc folder. You should see some folders and many .html files, including one for each class and one called "index.html".
11. Open "index.html" to view the Javadoc.

## Miscellaneous Notes
* This isn't full Javadoc. For full specifications you can refer to [Oracle's website](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html).
* eclipse can automatically generate many of these tags for you. This is done by typing the first line of Javadoc (`/**`) on an empty line before a method/class, then pressing enter.
```java
/**[enter]
public void methodName(){
    
}
```
### @deprecated 
* This indicates that a method is no longer being supported by its development team. Ideally, an alternate method will be indicated in the Javadoc as well.
* You will see this frequently when looking through any common Java libraries (e.g. Collections, DateTime (ugh), or the util package in general). As mentioned previously, these standard libraries should all have alternate methods recommended in their documentation.


## Reference
```java
/**
 * Description of class and its purpose. Be sure
 * to add new lines as necessary, since pretty
 * documentation is much less taxing to read.
 *
 * Sometimes you may even find it necessary to
 * add a second paragraph, and that is ok.
 *
 * @author Author Name
 * @version 0.1
 */
public class ClassName {

    /**
    * Summary sentence.
    * 
    * @param p1 describe parameter, include edge cases
    * @param p2 describe parameter, include edge cases
    * @return describe returned value. will likely be redundant
    * @throws UnlistedException warn why this could happen
    * @throws ExplicitException explain why this might happen
    */
    protected Type sampleMethod(Type p1, Type p2) throws ExplicitException {
        ...
        if(..){
            throw new UnlistedException();
        }
        ...
    }
}
```

## Example
```java
/**
 * Utility methods used for detection of duplicate values in arrays.
 * 
 * @author OUID
 * @version 0.1
 */
public class DuplicateSearch<T> {
    /**
    * Finds and returns any duplicates found over a range of indices.
    * 
    * @param arr        Array to be searched. Must have length > 0.
    * @param startIdx   Index to start searching at. Must be a valid index for 
    *                   arr.
    * @param endIdx     Index to stop searching at. Must be >= startIdx and a 
    *                   valid index for arr.
    * @return Linked list containing any duplicate values found
    * @throws NullPointerException if arr is null
    */
    public LinkedList<T> findDuplicatesFromTo(T[] arr, int startIdx, int endIdx);
}
```
