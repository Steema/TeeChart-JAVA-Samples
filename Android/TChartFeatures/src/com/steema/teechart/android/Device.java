
package com.steema.teechart.android;

// In device class we create the data of the linearlayout
// we differentiate the different linearlayout by their m_nToolType and their characteristics
// by their m_nDeviceType attribute
public class Device {
		   
	    private String m_szDeviceName;
	    private int m_nDeviceType;
	    private int m_nToolType;
	    public Device( String deviceName,int deviceType,int toolType) {
	        this.m_szDeviceName = deviceName;
	       this.m_nDeviceType = deviceType;
	       this.m_nToolType = toolType;

	      }


	    public String getDeviceName() { return m_szDeviceName; }
	    public void setDeviceName(String deviceName) { this.m_szDeviceName = deviceName;}
    
	    public int getDeviceType() { return m_nDeviceType; }
	    public void setDeviceType(int deviceType) { this.m_nDeviceType = deviceType;}
	    
	    public int getDeviceToolType() { return m_nToolType; }
	    public void setDeviceToolType(int toolType) { this.m_nToolType = toolType;}

}