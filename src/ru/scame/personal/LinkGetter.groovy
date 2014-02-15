package ru.scame.personal

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkGetter {
	protected Pattern htmltag;
	protected Pattern link;
	protected Pattern linkText;
	public LinkGetter() {
		initPatterns()
	}

	def initPatterns() {
		htmltag = Pattern.compile('<a\\b[^>]*href=[^>]\\S*>(.*?)</a>')
		link = Pattern.compile('href=[^>]*>')
		linkText = Pattern.compile('>(.*?)<')
	}

	def getLinks(String body) {
		def listOfPairLinkAndTitle = [];
		try {
			Matcher tagmatch = htmltag.matcher(body)
			while (tagmatch.find()) {
				Matcher matcher = link.matcher(tagmatch.group())
				Matcher textMatcher = linkText.matcher(tagmatch.group())
				matcher.find()
				textMatcher.find()
				String link = matcher.group().replaceFirst('href=', '').replaceFirst('>', '')
				String text = textMatcher.group().replaceFirst('>', '').replaceFirst('<', '')

				if (valid(link)) {
					listOfPairLinkAndTitle << [makeAbsolute(link), text, link]
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace()
		} catch (IOException e) {
			e.printStackTrace()
		}
		return listOfPairLinkAndTitle
	}

	protected boolean valid(String s) {
		return (s != '/' && s != '')
	}

	protected String makeAbsolute(String link) {
		return Constants.ROOT_URL + link
	}
}