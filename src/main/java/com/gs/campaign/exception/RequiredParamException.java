package com.gs.campaign.exception;

public class RequiredParamException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public RequiredParamException() {}
    
    public RequiredParamException(String message) {
	super(message);
    }

}
