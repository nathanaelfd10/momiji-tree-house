/**
 * 
 */
package com.noxfl.momijitreehouse.crawler;

/**
 * @author Fernando Nathanael
 *
 */
public interface SiteCrawlerFactory {

	public SiteCrawler getSiteCrawler(PageType pageType);

}