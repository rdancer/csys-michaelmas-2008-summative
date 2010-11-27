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

LATEX = latex
BIBTEX = bibtex
BASE = introduction-to-programming-michaelmas-2010-summative
TEX_FILE = $(BASE).tex
PDF_FILE = $(BASE).pdf
BIBTEX_DATA = references.bib

# Programs
# Is there a way to get this from MIME association database?
# -- yes: gvfs-open(1x)
VIEWER = evince

# untex(1) is not really precise.  The word count is give or take few words.
WORD_COUNT = $(shell untex -eo - < $(TEX_FILE) | wc -w)
WORD_COUNT_TARGET = 1800 # 450 words per question * 4 questions

# Just make all by default, and display the result
.PHONY: default
default: all

# Make everything
.PHONY: all
all: $(PDF_FILE) wordcount

# Make the PDF file
.PHONY: dvi
pdf: $(PDF_FILE)
$(PDF_FILE): $(TEX_FILE) $(BIBTEX_DATA) ./*.png
	$(LATEX)	$(TEX_FILE)	||:
	$(BIBTEX)	$(BASE)		||:
	$(LATEX)	$(TEX_FILE)	||:
	$(BIBTEX)	$(BASE)		||:
	$(LATEX)	$(TEX_FILE)	||:
	$(LATEX)	$(TEX_FILE)	||:

# Remove all that can be re-made (sans the useful files -- cf. make mrproper)
.PHONY: clean
clean:
	rm -f -- $(BASE).aux
	rm -f -- $(BASE).log
	rm -f -- $(BASE).bbl
	rm -f -- $(BASE).blg

# Remove even the useful files
.PHONY: mrproper
mrproper: clean
	rm -f -- $(PDF_FILE)

.PHONY: view
view: all
	$(VIEWER) $(PDF_FILE)

# Print on the printer
.PHONY: hardcopy
hardcopy: $(DVI_FILE)
	a2ps -1 $(DVI_FILE)

# Print number of words written and how much needs yet to be written
.PHONY: wordcount
wordcount: $(TEX_FILE)
	printf 'Word count: %d/%d (%d%%)' $(WORD_COUNT) $(WORD_COUNT_TARGET) $$(( $(WORD_COUNT) * 100 / $(WORD_COUNT_TARGET) ))
