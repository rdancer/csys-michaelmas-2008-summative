# Makefile -- Main make file for this project
#
#   Copyright © 2008–2010 Jan Minář <rdancer@rdancer.org>
#
#   This program is free software; you can redistribute it and/or modify
#   it under the terms of the GNU General Public License version 2 (two),
#   as published by the Free Software Foundation.
#
#   This program is distributed in the hope that it will be useful,
#   but WITHOUT ANY WARRANTY; without even the implied warranty of
#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#   GNU General Public License for more details.
#
#   You should have received a copy of the GNU General Public License along
#   with this program; if not, write to the Free Software Foundation, Inc.,
#   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.


# Documentation is handled separately from the code compilation.  Ideally,
# the documentation would be kept in sync.


PROJECT_NAME = MusicLibrary
# The class with the entry point (we use the test class)
MAIN_CLASS = Main
# All the classes
CLASSES = $(MAIN_CLASS) Album Artist Library Track

#SUBCLASSES = `find . -maxdepth 1 -type f -name '?*$$?*.class'` # Brittle
# Source files needed to build this project
SOURCE_FILES = $(shell git ls-files) # XXX Does this handle subdirectories?
TEST_OUTPUT_CORRECT = test-output
JAVAC = javac -Xlint
DOCUMENTATION_DIRECTORY = ./doc
DEFINE_SWING_LOOK_AND_FEEL =
BROWSER = $${BROWSER:-firefox}

# By default, make the jar file
.PHONY: all
all: $(PROJECT_NAME).jar $(PROJECT_NAME)-src.jar

# Rebuild from scratch
.PHONY: allclean
allclean: clean all

# Run the program
.PHONY: run
run: all
	java $(DEFINE_SWING_LOOK_AND_FEEL) -jar $(PROJECT_NAME).jar

# Run tests
.PHONY: test
test: all
	java $(DEFINE_SWING_LOOK_AND_FEEL) -jar $(PROJECT_NAME).jar \
		| diff - -- $(TEST_OUTPUT_CORRECT) \
	    && echo "Test OK"

# Test the GTK Swing skin
.PHONY: test-gtk
test-gtk: DEFINE_SWING_LOOK_AND_FEEL := -Dswing.defaultlaf=com.sun.java.swing.plaf.gtk.GTKLookAndFeel
test-gtk: test

# Start the program inside a debugger
.PHONY: debug
debug: all
	jdb $(MAIN_CLASS)

# Delete all we can remake (except documentation)
.PHONY: clean
clean:
	# The jar files
	rm -f -- $(PROJECT_NAME).jar
	rm -f -- $(PROJECT_NAME)-src.jar
	# The classes
	rm -f -- Manifest.txt
	rm -f -- $(patsubst %,%.class,$(CLASSES))
	find . -maxdepth 1 -type f -name '?*$$?*.class' -exec rm -- {} \; # Robust

# The jar archive that lumps it all together
# FIXME Is .PHONY because $(SUBCLASSES) can't be use in prerequisites
.PHONY: $(PROJECT_NAME).jar
$(PROJECT_NAME).jar: Manifest.txt $(patsubst %,%.class,$(CLASSES))
	jar cfm $(PROJECT_NAME).jar \
		Manifest.txt \
		$(patsubst %,%.class,$(CLASSES)) \
		$(SUBCLASSES)
	# Decent operating systems can execute JARs directly
	chmod +x $(PROJECT_NAME).jar 



# The jar with all the source code -- should ensure GNU GPL compliance
$(PROJECT_NAME)-src.jar: $(SOURCE_FILES)
	jar cf $(PROJECT_NAME)-src.jar \
	    	Manifest.txt \
		$(SOURCE_FILES)
		
# All the classes
$(patsubst %,%.class,$(CLASSES)): $(patsubst %,%.java,$(CLASSES))
	$(JAVAC) $(patsubst %.class,%.java,$@)


# We can remake Manifest.txt rather easily
# Depend on Makefile, because the contents depends on the MAIN_CLASS
# variable.
Manifest.txt: Makefile
	rm -f -- Manifest.txt
	test \! -e Manifest.txt
	echo "Main-Class: "$(MAIN_CLASS) > Manifest.txt


# Documentation
#
# TODO: Rebuild only as much as is necessary, so that we can rebuild whenever
# the source is changed, thereby enabling keeping the documentation in sync
# with the rest of the project.
.PHONY: doc
doc: doc-clean
	javadoc -d $(DOCUMENTATION_DIRECTORY) ./*.java

# View documentation in $BROWSER
.PHONY: viewdoc
viewdoc: doc
	$(BROWSER) doc/index.html
	
# Remove all documentation that can be remade
.PHONY: doc-clean
doc-clean:
	rm -rf -- $(DOCUMENTATION_DIRECTORY)
	mkdir -- $(DOCUMENTATION_DIRECTORY)
