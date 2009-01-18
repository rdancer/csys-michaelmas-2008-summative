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
BASE = computer-systems-phishing-formative
TEX_FILE = $(BASE).tex
DVI_FILE = $(BASE).dvi
BIBTEX_DATA = tau.bib

# Programs
# Is there a way to get this from MIME association database?
# — yes:
# <http://library.gnome.org/devel/gnome-vfs-2.0/unstable/gnome-vfs-20-gnome-vfs-mime-database.html#gnome-vfs-mime-get-default-application>
# — but who knows how to interface with that.  Not worth the effort, and I
# don't have the time — rdancer 2009-01-18
# — also, xdg-mime(1).  But it didn't really work, so fuck it!
#VIEWER = /usr/lib/kde4/bin/okular  # Is not in $PATH
	# Too slow
VIEWER = xdvi  # Is not in $PATH

.PHONY: default
default: $(DVI_FILE)

$(DVI_FILE): $(TEX_FILE) $(BIBTEX_DATA)
	$(LATEX)	$(TEX_FILE)
	$(BIBTEX)	$(BASE)
	$(LATEX)	$(TEX_FILE)
	$(LATEX)	$(TEX_FILE)

.PHONY: clean
clean:
	rm -f -- $(DVI_FILE)
	rm -f -- $(BASE).aux
	rm -f -- $(BASE).log
	rm -f -- $(BASE).bbl
	rm -f -- $(BASE).blg

.PHONY: view
view: $(DVI_FILE)
	$(VIEWER) $(DVI_FILE)
