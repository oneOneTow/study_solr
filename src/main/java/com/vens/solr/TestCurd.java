package com.vens.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/9/18
 */
public class TestCurd {
    public static final String url = "http://localhost:8080/solr/core_0";
    public static void writeData() throws IOException, SolrServerException {
        HttpSolrClient hsc=new HttpSolrClient(url);
        SolrInputDocument sid=new SolrInputDocument();
        sid.addField("id",1);
        sid.addField("solr_s","this is a solr hello word,thank you!");
        hsc.add(sid);
        hsc.commit();
        hsc.close();
    }
    public static void readData() throws IOException, SolrServerException {
        HttpSolrClient hsc= new HttpSolrClient(url);
        SolrQuery sq=new SolrQuery();
        sq.setQuery("solr_s:*");
        sq.set("sort","id asc");
        sq.setStart(0);
        sq.setRows(1);
        SolrDocumentList sdl = hsc.query(sq).getResults();
        for(SolrDocument sd:sdl){
            System.out.println(sd.getFieldValue("name_s"));
        }
        hsc.close();
    }
    public static void testDelDoc() throws SolrServerException, IOException{
        HttpSolrClient hsc=new HttpSolrClient(url);
        hsc.deleteById("1");
        hsc.commit();
        hsc.close();
    }
    public static void main(String [] args){
        try {
            TestCurd.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
