# Makefile -- Main make file for this project
#
#   Copyright © 2008–2009 Jan Minář <rdancer@rdancer.org>
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

LATEX = latex
BIBTEX = bibtex
BASE = computer-systems-michaelmas-summative
TEX_FILE = $(BASE).tex
DVI_FILE = $(BASE).dvi
POSTSCRIPT_FILE = $(BASE).ps
PDF_FILE = $(BASE).pdf
BIBTEX_DATA = references.bib

# Programs
# Is there a way to get this from MIME association database?
# -- yes: gvfs-open(1x)
#VIEWER = /usr/lib/kde4/bin/okular  # Is not in $PATH
	# Too slow
VIEWER = xdvi -geometry -0-0 # Bottom right corner

# untex(1) is not really precise.  The word count is give or take few words.
WORD_COUNT = $(shell untex -eo - < $(TEX_FILE) | wc -w)
WORD_COUNT_TARGET = 1800 # 450 words per question * 4 questions

PNG_RESOLUTION = 300  # dpi
DVIPNG = dvipng -D$(PNG_RESOLUTION)


# Just make all by default, and display the result
.PHONY: default
default: all

# Make everything
.PHONY: all
all: $(DVI_FILE) $(POSTSCRIPT_FILE) $(PDF_FILE) wordcount

# Make the DVI file
.PHONY: dvi
dvi: $(DVI_FILE)
$(DVI_FILE): $(TEX_FILE) $(BIBTEX_DATA)
	$(LATEX)	$(TEX_FILE)	||:
	$(BIBTEX)	$(BASE)		||:
	$(LATEX)	$(TEX_FILE)	||:
	$(BIBTEX)	$(BASE)		||:
	$(LATEX)	$(TEX_FILE)	||:
	$(LATEX)	$(TEX_FILE)	||:

# Make the PostScript file
.PHONY: ps
ps: $(POSTSCRIPT_FILE)
$(POSTSCRIPT_FILE): $(DVI_FILE)
	dvips $(DVI_FILE)

# Make the PDF file
.PHONY: pdf
pdf: $(PDF_FILE)
$(PDF_FILE): $(DVI_FILE)
	dvipdf $(DVI_FILE)

# Distill one PNG for every page — to be pasted into a Microsoft Word document
.PHONY: pngs
pngs: $(DVI_FILE)
	#rm -rf -- $(PNG_DIRECTORY)
	#mkdir -- $(PNG_DIRECTORY)
	$(DVIPNG) $(BASE).dvi

# Remove all that can be re-made
.PHONY: clean
clean:
	rm -f -- $(DVI_FILE)
	rm -f -- $(POSTSCRIPT_FILE)
	rm -f -- $(PDF_FILE)
	rm -f -- $(BASE).aux
	rm -f -- $(BASE).log
	rm -f -- $(BASE).bbl
	rm -f -- $(BASE).blg
	# dvipng(1) names the files according to the source file basename:
	# FOO.dvi -> FOO<page_number>.png
	rm -f -- $(BASE)?*.png

# Display the resulting document
.PHONY: view
view: all
	$(VIEWER) $(DVI_FILE)

# Display the resulting document, in the background
.PHONY: view
viewbg: all
	($(VIEWER) $(DVI_FILE) >/dev/null 2>&1 &)

# Print on the printer
.PHONY: hardcopy
hardcopy: $(DVI_FILE)
	a2ps -1 $(DVI_FILE)

# Print number of words written and how much needs yet to be written
.PHONY: wordcount
wordcount: $(TEX_FILE)
	printf 'Word count: %d/%d (%d%%)' $(WORD_COUNT) $(WORD_COUNT_TARGET) $$(( $(WORD_COUNT) * 100 / $(WORD_COUNT_TARGET) ))
