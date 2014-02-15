package ru.scame.personal

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class OsoParser {
	
	static void main(def args){
		String utfBody = ParserUtils.urlToUtfString(Constants.ROOT_URL.toURL())
		
		LinkGetter linkGetter = new LinkGetter();
		def mainPageLinks = linkGetter.getLinks(utfBody)
		
		PageSaver pageSaver = new PageSaver()
		pageSaver.savePage(utfBody, '0__1Главная', mainPageLinks)
		
		
		OsoSubThemeParser subThemeParser = new OsoSubThemeParser(mainPageLinks)
		subThemeParser.parseSubThemes()
		
		println 'script finished'
	}
	

}
