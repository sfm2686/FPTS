/**
 * 
 */
package GUI;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WItemLabel extends javax.swing.JLabel {

	public WItemLabel(String text){
		this.setText(text);
	}
	
	@Override
	public String toString(){
		return this.getText();
	}
}
