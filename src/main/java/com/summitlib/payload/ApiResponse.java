package com.summitlib.payload;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse<T> {
	
	    private  int status;
	    private  String message;
	    private T data;
	    private  String timestamp;
	    private Map<String, String> links;
	    
	    public ApiResponse() {}
	    
		public ApiResponse(int status, String message, T data, String timestamp, Map<String, String> links) {
			this.status = status;
			this.message = message;
			this.data = data;
			this.timestamp = timestamp;
			this.links = links;
		}
		
		public ApiResponse(int status, String message, T data, String timestamp) {
			this.status = status;
			this.message = message;
			this.data = data;
			this.timestamp = timestamp;
		}


		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public Map<String, String> getLinks() {
			return links;
		}

		public void setLinks(Map<String, String> links) {
			this.links = links;
		}
		
		public ApiResponse<T> addLink(String rel, String href) {
			if(this.links == null) {
				this.links = new HashMap<String, String>();
			}
			
	        this.links.put(rel, href);
	        return this;
	    }
}
