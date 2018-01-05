package cn.itcast.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrDemo {
	
	public static void main(String[] args) throws SolrServerException, IOException {
		
		String url = "http://127.0.0.1:8983/solr/";
		
		HttpSolrServer server = new HttpSolrServer(url);
		
		//2.创建SolrInputDocument对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "123");
		document.addField("name", "陆军马路");
		
		server.add(document);
		
		server.commit();
		
	}
	
	
	@Test
	public void testQuery() throws Exception{
		String url = "http://127.0.0.1:8983/solr/";
		HttpSolrServer httpSolrServer = new HttpSolrServer(url);
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		QueryResponse response = httpSolrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		System.out.println("搜索到的结果总数："+results.getNumFound());
		
		for (SolrDocument solrDocument : results) {
			Object object = solrDocument.get("id");
			System.out.println(object);
			
			Object object2 = solrDocument.get("name");
			System.out.println(object2);
		}	
	}
	
	@Test
	public void testDelete() throws SolrServerException, IOException{
		
		String url = "http://127.0.0.1:8983/solr/";
		HttpSolrServer httpSolrServer = new HttpSolrServer(url);
		
		httpSolrServer.deleteByQuery("*:*");
		
		httpSolrServer.commit();
		
	}
	
	
	

	
	
	
	

}
