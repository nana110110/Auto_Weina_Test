package Core.WebCore;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Core.Log;


public class WebItem {
	
	private WebElement item;
	public String locator;
	public By byId;
	
	public WebElement getItem(int seconds) {
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), seconds);
		item = wait.until(ExpectedConditions.elementToBeClickable(byId));
		return item;
	}
	
	public WebElement getItem() {
		return getItem(3);
	}
	
	public WebItem(By byID){
		String idString = byID.toString();
		locator = idString.substring(idString.indexOf(":")+1, idString.length()).trim();
		byId=byID;
	}
	
	public boolean exists(int waitTime){
		boolean result = false;
		try{
			result=getItem(waitTime)!=null;
		}catch(Exception e){}
		Log.info("'"+locator+ "' existance verification. Exists = "+result);
		return result;
	}
	
	public void click(){
		Log.info("Clicking on '"+locator+"'");
		getItem().click();
	}
	
	//function added by weina
	public void movetoclick(){
		Log.info("Moving to and clicking on '"+locator+"'");
		Actions action = new Actions(Browser.getDriver());
		action.moveToElement(getItem()).click();
		action.perform();
	}
	
	public boolean isEnabled(){
		boolean out =this.getItem().isEnabled();
		Log.info("'"+locator + "' isEnabled verification: Enabled = "+out);
		return out;
	}
	
	public boolean isDisplayed(){
		boolean out =this.getItem().isDisplayed();
		Log.info("'"+locator + "' isDisplayed verification: Displayed = "+out);
		return out;
	}
	
	public void clear(){
		Log.info("Clearing '"+locator+"'");
		this.getItem().clear();
	}
	
	public String getText(){
		String textOut=this.getItem().getText();
		Log.info("Getting text from '"+locator +"'. ActualText = "+textOut);
		return textOut;
	}
	
	//function added by weina
	public boolean isChecked()
    {
		boolean out =this.getItem().isSelected();
		Log.info("'"+locator + "' isChecked verification: Checked = "+out);
		return out;
    }
	
	//function added by weina
	public void changeattribute(String attrName, String value)
    {
		getItem();
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("arguments[0].setAttribute('"+attrName+"', '"+value+"')", item);
        Log.info("Changing attibute for '"+locator +"'. AttributeName= '"+attrName+"'. NewValue= '"+value+"'.");
    }
    
	//function added by weina
    public void changeinnerHTML(String value)
    {
    	   getItem();
           JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
           js.executeScript("arguments[0].innerHTML ='"+value+"'", item);
           Log.info("Changing innerHTML for '"+locator +"'. NewValue= '"+value+"'.");
    }

}
