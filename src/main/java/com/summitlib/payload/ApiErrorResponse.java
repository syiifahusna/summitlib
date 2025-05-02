package com.summitlib.payload;


public class ApiErrorResponse<T> {
	
	 	private int status;
	    private  String error;
	    private  T messages;
	    private  String timestamp;
	    
	    
	    public ApiErrorResponse() {}


		public ApiErrorResponse(int status, String error, T messages, String timestamp) {
			super();
			this.status = status;
			this.error = error;
			this.messages = messages;
			this.timestamp = timestamp;
		}


		public int getStatus() {
			return status;
		}


		public void setStatus(int status) {
			this.status = status;
		}


		public String getError() {
			return error;
		}


		public void setError(String error) {
			this.error = error;
		}


		public T getData() {
			return messages;
		}


		public void setData(T messages) {
			this.messages = messages;
		}


		public String getTimestamp() {
			return timestamp;
		}


		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		
		
	    
		
	    

}
