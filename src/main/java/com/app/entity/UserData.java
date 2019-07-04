package com.app.entity;

public class UserData {

    private String type;
    private String content;
    private String type_value;
		 
	    public UserData() {
	 
	    }
	 
	    public UserData(String type, String content, String type_value) {
	        super();
	        this.type = type;
	        this.content = content;
	        this.type_value = type_value;
	    }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getType_value() {
			return type_value;
		}

		public void setType_value(String type_value) {
			this.type_value = type_value;
		}

		@Override
		public String toString() {
			return "UserData [type=" + type + ", content=" + content
					+ ", type_value=" + type_value + "]";
		}
	  
		
}
