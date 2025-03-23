package com.summitlib.payload;

public class ApiErrorResponse<T> {
	
	 	private int status;
	    private  String error;
	    private  T data;
	    private  String timestamp;
	    
	    
	    public ApiErrorResponse() {}


		public ApiErrorResponse(int status, String error, T data, String timestamp) {
			super();
			this.status = status;
			this.error = error;
			this.data = data;
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
		
		
	    
		
	    

}
