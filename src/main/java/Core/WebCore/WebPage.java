package Core.WebCore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import Core.Log;



public abstract class WebPage{
	
	private String pageName = this.getClass().getName();
	protected String[] invokeArgs;
	protected abstract By webPageId();
	protected abstract void invoke();
	
	public void initElements() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
/*		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f: fields){
			Object id= new Annotations(this.getClass().getDeclaredField(f.getName())).buildBy();
			Constructor<?>[] constr = f.getType().getConstructors();
			if (id.getClass().getSimpleName().equals("ByChained")){
				ArrayList<By> byList = Custom.byChainedToByList(id);
				for(Constructor<?> constructor: constr){
					if(constructor.getParameterCount()==byList.size()){
						f.set(this, constructor.newInstance(byList.toArray()));
						break;
					}
				}	
			}else{
				f.set(this, constr[0].newInstance(id));
			}
		}*/
	}
	
	public WebPage(){
		try{
			initElements();	
		}catch(Exception e){
			e.printStackTrace();
		}
		if (!exists(0)){
			Log.info("Invoking '"+pageName+"' page");
			invoke();
			//AssertLogger.assertTrue(exists(10), pageName + " does not exists after invoke attempt in 10 seconds");
		}
	}
	
	public WebPage(String... invokeArgs){
		try{
			initElements();	
		}catch(Exception e){
			e.printStackTrace();
		}
		this.invokeArgs=invokeArgs;
		if (!exists(0)){
			Log.info("Invoking '"+pageName+"' page");
			invoke();
			exists(10);
			Browser.waitForPageToLoad(10);
		}
	}
	
	public boolean exists(int waitTime){
		boolean exists = false;
		WebDriver dr = Browser.getDriver();
		waitTime = waitTime > 0 ? waitTime * 1000 : waitTime;
		long startTime = System.currentTimeMillis();
		do {
			WebElement element = null;
			try {
				element = dr.findElement(webPageId());
			} catch (Exception e) {
				//e.printStackTrace();
			}
			if (element != null) {
				try {
					if (element.isDisplayed()) {
						exists = true;
					}
				} catch (Exception e) {
				}
			}
		} while (!exists && System.currentTimeMillis() - startTime <= waitTime);
		Log.info("'" + pageName + "' page existance verification. Exists = " + exists);
		return exists;
	}
	
}
 