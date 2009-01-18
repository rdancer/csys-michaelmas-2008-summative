LATEX = latex
BIBTEX = bibtex
BASE = computer-systems-phishing-formative
TEX_FILE = $(BASE).tex
DVI_FILE = $(BASE).dvi
BIBTEX_DATA = tau.bib

.PHONY: default
default: $(DVI_FILE)

.PHONY: $(DVI_FILE)
$(DVI_FILE): $(SOURCE_TEX) $(BIBTEX_DATA)
	$(LATEX)	$(TEX_FILE)
	$(BIBTEX)	$(BASE)
	$(LATEX)	$(TEX_FILE)
	$(LATEX)	$(TEX_FILE)

clean:
	rm -f -- $(DVI_FILE)
	rm -f -- $(BASE).aux
	rm -f -- $(BASE).log
	rm -f -- $(BASE).bbl
	rm -f -- $(BASE).blg
