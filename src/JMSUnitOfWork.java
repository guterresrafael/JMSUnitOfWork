import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

public class JMSUnitOfWork {

	public static XmlObject parse(Object object) {
		try {
			ArrayList messagelist = (ArrayList) object;
			BytesMessage byteMessage = (BytesMessage) messagelist.get(messagelist.size() - 1);
			byte[] byteArr = new byte[(int)byteMessage.getBodyLength()];
			byteMessage.readBytes(byteArr);
			String message = new String(byteArr, "UTF-8");  
			XmlObject xmlObject = XmlObject.Factory.parse(message);
			return xmlObject;
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
