package ru.scame.personal

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SubThemeLinkGetter extends LinkGetter {
	
	public SubThemeLinkGetter() {
		initPatterns();

	}
	
	def getPagesForSubTheme(String body, String subThemeName){
		htmltag = Pattern.compile('<a\\b[^>]*href=[^>]\\S*>[0-9]</a>')
		def pages = getLinks(body)
		return pages;
	}
}
