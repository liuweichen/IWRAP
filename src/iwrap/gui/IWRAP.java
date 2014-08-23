package iwrap.gui;
import iwrap.aground.FDrifting;
import iwrap.aground.FOne;
import iwrap.aground.FTwo;
import iwrap.collision.CollisionFrequency;
import iwrap.collision.Na;
import iwrap.util.CausasionFactor;
import iwrap.util.Channel;
import iwrap.util.Distribution;
import iwrap.util.Sailor;
import iwrap.util.Ship;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class IWRAP extends JFrame {
	String filaDirectionSource;
	String fileNameSource;//source data源数据
	String fileDirectionResult;
	String fileNameResult;//result data结果数据
	Ship[] ship;
	Channel[] channel;
	Distribution[] dist;
	int rowCount;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IWRAP frame = new IWRAP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IWRAP() {
		setResizable(false);
		
		
		setTitle("IWRAP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 864);
		contentPane = new JPanel();
		contentPane.setLocation(0, -183);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, null, null));
		panel.setBounds(10, 38, 689, 111);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u5BF9\u9047\u78B0\u649E\u7CFB\u6570\uFF1A");
		label.setFont(new Font("SimSun", Font.PLAIN, 16));
		label.setBounds(10, 10, 112, 21);
		panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setText("0.00005");
		textField_1.setColumns(10);
		textField_1.setBounds(121, 9, 97, 25);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("\u8FFD\u8D8A\u78B0\u649E\u7CFB\u6570\uFF1A");
		label_2.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_2.setBounds(10, 45, 112, 21);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setText("0.00011");
		textField_2.setColumns(10);
		textField_2.setBounds(121, 44, 97, 25);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("\u4EA4\u53C9\u78B0\u649E\u7CFB\u6570\uFF1A");
		label_3.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_3.setBounds(10, 77, 112, 21);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setText("0.00013");
		textField_3.setColumns(10);
		textField_3.setBounds(121, 76, 97, 25);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("\u8239\u8236\u5931\u63A7\u9891\u7387\uFF1A");
		label_4.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_4.setBounds(229, 10, 144, 21);
		panel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setText("0.75");
		textField_4.setColumns(10);
		textField_4.setBounds(366, 8, 112, 25);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("\u9ED8\u8BA4\u8239\u8236\u5931\u63A7\u9891\u7387\uFF1A");
		label_5.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_5.setBounds(229, 42, 144, 21);
		panel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setText("0.1");
		textField_5.setColumns(10);
		textField_5.setBounds(366, 40, 112, 25);
		panel.add(textField_5);
		
		JLabel label_6 = new JLabel("\u68C0\u67E5\u8239\u4F4D\u7684\u8DDD\u79BB\uFF1A");
		label_6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_6.setBounds(228, 77, 128, 21);
		panel.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setText("1000");
		textField_6.setColumns(10);
		textField_6.setBounds(366, 76, 113, 25);
		panel.add(textField_6);
		
		JButton button = new JButton("\u786E\u8BA4\u8BBE\u7F6E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CausasionFactor.headOnCollision =Float.parseFloat(textField_1.getText()); 
				CausasionFactor.overtakeCollision=Float.parseFloat(textField_2.getText()); 
				CausasionFactor.crossCollision=Float.parseFloat(textField_3.getText()); 
				CausasionFactor.frequencyOutofControl=Float.parseFloat(textField_4.getText())/(360*24*3600); 
				CausasionFactor.frequencyOutofControlDefault=Float.parseFloat(textField_5.getText())/(360*24*3600); 
				CausasionFactor.agroundFactor=Float.parseFloat(textField.getText());
				Sailor.distanceCheckPosition=Double.parseDouble(textField_6.getText());
				JOptionPane.showMessageDialog( getParent(), "系数设置成功");
			}
		});
		button.setFont(new Font("SimSun", Font.PLAIN, 15));
		button.setBounds(495, 63, 184, 35);
		panel.add(button);
		
		textField = new JTextField();
		textField.setText("0.00016");
		textField.setColumns(10);
		textField.setBounds(574, 10, 105, 25);
		panel.add(textField);
		
		JLabel label_10 = new JLabel("\u6401\u6D45\u7CFB\u6570\uFF1A");
		label_10.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_10.setBounds(495, 10, 85, 21);
		panel.add(label_10);
		
		JLabel label_1 = new JLabel("\u4E8B\u6545\u56E0\u5B50\u8BBE\u7F6E\uFF1A");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_1.setBounds(10, 10, 112, 25);
		contentPane.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, null, null));
		panel_1.setBounds(10, 159, 689, 52);
		contentPane.add(panel_1);
		
		JLabel label_12 = new JLabel("\u6587\u4EF6\u76EE\u5F55\uFF1A");
		label_12.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_12.setBounds(162, 16, 80, 21);
		panel_1.add(label_12);
		
		final JLabel label_9 = new JLabel("");
		label_9.setForeground(SystemColor.desktop);
		label_9.setBackground(SystemColor.inactiveCaption);
		label_9.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_9.setBounds(252, 16, 427, 21);
		panel_1.add(label_9);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, null, null));
		panel_2.setBounds(10, 377, 689, 52);
		contentPane.add(panel_2);

		
		final JLabel label_15 = new JLabel("");
		label_15.setForeground(Color.BLACK);
		label_15.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_15.setBackground(SystemColor.inactiveCaption);
		label_15.setBounds(249, 16, 430, 21);
		panel_2.add(label_15);
		
		JButton button_6 = new JButton("\u9009\u62E9\u7ED3\u679C\u6587\u4EF6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fileNameExtensionFilter = 
						new FileNameExtensionFilter("accdb&xlsx", "accdb", "xlsx");
				fileChooser.setFileFilter(fileNameExtensionFilter);
				int returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileDirectionResult = fileChooser.getCurrentDirectory().toString();
					fileNameResult = fileChooser.getSelectedFile().getName();
					label_15.setText(fileDirectionResult + fileNameResult);
				}
			}
		});
		button_6.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_6.setBounds(10, 10, 145, 34);
		panel_2.add(button_6);
		
		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(12, 641, 687, 109);
		contentPane.add(textArea_2);
		JScrollPane scrollPane_2 = new JScrollPane(textArea_2);
		scrollPane_2.setBounds(10, 639, 689, 111);
		contentPane.add(scrollPane_2);
		
		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 485, 687, 109);
		contentPane.add(textArea_1);
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setBounds(10, 483, 689, 111);
		contentPane.add(scrollPane_1);
		
		JLabel label_14 = new JLabel("\u6587\u4EF6\u76EE\u5F55\uFF1A");
		label_14.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_14.setBounds(165, 16, 80, 21);
		panel_2.add(label_14);
		

		
		JButton button_2 = new JButton("\u9009\u62E9\u6570\u636E\u6E90\u6587\u4EF6");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fileNameExtensionFilter = 
						new FileNameExtensionFilter("accdb&xlsx", "accdb", "xlsx");
				fileChooser.setFileFilter(fileNameExtensionFilter);
				int returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					filaDirectionSource = fileChooser.getCurrentDirectory().toString();
					fileNameSource = fileChooser.getSelectedFile().getName();
					label_9.setText(filaDirectionSource + fileNameSource);
				}
			}
		});
		button_2.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_2.setBounds(10, 10, 142, 34);
		panel_1.add(button_2);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 256, 687, 111);
		contentPane.add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 256, 689, 111);
		contentPane.add(scrollPane);
		
		JButton button_1 = new JButton("\u663E\u793A\u6570\u636E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * 直接连接Excel和Accdb文件。
					 */
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					String dbur1;
					if(fileNameSource.contains("xlsx")||fileNameSource.contains("xls")) {
						dbur1 = "jdbc:odbc:driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="
								+filaDirectionSource+fileNameSource;
					} else if(fileNameSource.contains("accdb")||fileNameSource.contains("mdb")) {
						dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
								+filaDirectionSource+fileNameSource;
					} else {
						dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
								+filaDirectionSource+fileNameSource;
						JOptionPane.showMessageDialog( getParent(), "系统加载数据库驱动程序错误");
					}
					Connection conn = DriverManager.getConnection(dbur1, "username", "password");
					Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs;
					if(fileNameSource.contains("xlsx")||fileNameSource.contains("xls")) {
						rs = stmt.executeQuery("select * from [IWRAPDataSource$]");
					} else if(fileNameSource.contains("accdb")||fileNameSource.contains("mdb")) {
						rs = stmt.executeQuery("select * from IWRAPDataSource");
					} else {
						rs = stmt.executeQuery("select * from IWRAPDataSource");
						JOptionPane.showMessageDialog( getParent(), "系统加载数据库数据表错误");
					}
					rs.last();
					rowCount = rs.getRow();
					ship = new Ship[rowCount];
					channel = new Channel[rowCount];
					dist = new Distribution[rowCount];
					rs.beforeFirst();
					int number = 0;
					while (rs.next()) {
						//System.out.println(rs.getString(1));
						ship[number] = new Ship();
						ship[number].setType(Ship.getShipKind(rs.getString(1)));
						ship[number].setLength(rs.getFloat(2));
						ship[number].setWidth(rs.getFloat(3));
						ship[number].setCourse(rs.getFloat(4));
						ship[number].setSpeed(rs.getFloat(5));
						ship[number].setQuantityofShip(rs.getInt(6));
						
						dist[number] = new Distribution();
						dist[number].setDk(Distribution.getDistributionKind(rs.getString(7)));
						dist[number].setMeanValue(rs.getFloat(8));
						dist[number].setVariance(rs.getFloat(9));
						dist[number].setMin(rs.getFloat(10));
						dist[number].setMax(rs.getFloat(11));
						dist[number].setMinIntegration(rs.getFloat(12));
						dist[number].setMaxIntegration(rs.getFloat(13));
						
						ship[number].setDist(dist[number]);
						
						channel[number] = new Channel();
						channel[number].setLength(rs.getFloat(14));
						channel[number].setWidth(rs.getFloat(15));
						channel[number].setDistanceObstacle(rs.getFloat(16));
						channel[number].setMinObstacle(rs.getFloat(17));
						channel[number].setMaxObstacle(rs.getFloat(18));
						textArea.append(ship[number].toString()+"***"+dist[number].toString()
								+"****"+channel[number].toString()+"\r\n");
						number++;
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}			
		});
		
		JLabel label_8 = new JLabel("\u539F\u59CB\u6570\u636E\u663E\u793A\uFF1A");
		label_8.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_8.setBounds(10, 221, 112, 25);
		contentPane.add(label_8);
		button_1.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_1.setBounds(120, 221, 103, 22);
		contentPane.add(button_1);
		
		JLabel label_11 = new JLabel("\u78B0\u649E\u6982\u7387\u663E\u793A\uFF1A");
		label_11.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_11.setBounds(10, 448, 112, 25);
		contentPane.add(label_11);
		
		JButton button_3 = new JButton("\u8BA1\u7B97\u78B0\u649E\u6982\u7387");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
							+fileDirectionResult+fileNameResult;
					Connection conn = DriverManager.getConnection(dbur1, "username", "password");
					Statement stmt = conn.createStatement();
					 
					int allCount = rowCount*(rowCount - 1)/2;
					int tempCount = 0;
					float[] collisionFrenquency = new float[allCount];
					float resultCollisionFrequency = 0.0f;
					for(int i = 0; i < rowCount; i++) {
						for(int j = i+1; j < rowCount; j++) {
							//get collision frequency is depended on channel length and width,
							//we assume all the ships in the same channel
							//假设所以船舶都是同一航道，航道信息长度和宽度一样
							Na na = new Na(ship[i],ship[j],channel[i]);
							collisionFrenquency[tempCount] = CollisionFrequency.getCollisionFrequency(na);
							resultCollisionFrequency +=collisionFrenquency[tempCount];
							textArea_1.append(ship[i].toString()+"\r\n"+ship[j].toString()+"\r\n");
							textArea_1.append(collisionFrenquency[tempCount]+"\r\n");
							String tempI = ship[i].toString();
							String tempJ = ship[j].toString();
							//String tempID = String.valueOf(tempCount);
//							stmt.executeUpdate("insert into CollisionFrequency "
//									+ "(ShipKindOne,ShipKindTwo,CollisionFrequency) values"
//									+ " (1000,32000,122)");
							stmt.executeUpdate("insert into CollisionResult "
									+ "(ID,ShipKindOne,ShipKindTwo,CollisionFrequency) values "
									+ "('"+tempCount+"','"+tempI+"','"+tempJ+"','"
									+collisionFrenquency[tempCount]+"')");
							tempCount++;
						}
					}
					textArea_1.append("\r\n*********\r\n"
										+resultCollisionFrequency +"\r\n*********");
					stmt.close();
					conn.close();
					JOptionPane.showMessageDialog( getParent(),
							"碰撞概率数据保存在"+fileDirectionResult+fileNameResult);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_3.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_3.setBounds(120, 450, 133, 22);
		contentPane.add(button_3);
		
		//for test
		JButton button_4 = new JButton("\u6D4B\u8BD5\u6309\u94AE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
							+fileDirectionResult+fileNameResult;
					Connection conn = DriverManager.getConnection(dbur1, "username", "password");
					Statement stmt = conn.createStatement();
					 
					textArea_1.append("*********\r\n"+fileDirectionResult+fileNameResult+"\r\n*********");
							stmt.executeUpdate("insert into CollisionResult"
									+ "(ShipKindOne,ShipKindTwo,CollisionFrequency) values"
									+ " ('LNG','LPG',122)");
//							stmt.executeUpdate("insert into CollisionResult"
//									+ "(ShipKindOne,ShipKindTwo,CollisionFrequency) values"
//									+ " (1000,32000,122)");
//							stmt.executeUpdate("insert into CollisionFrequency "
//									+ "(ShipKindOne,ShipKindTwo,CollisionFrequency) values "
//									+ "("+tempI+","+tempJ+","+collisionFrenquency[tempCount]+")");
					stmt.close();
					conn.close();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			
//				Distribution distC = new Distribution(DistributionKind.normalDistribution
//						,450f,100,300f,600f,550f,600f);
//				Ship container = new Ship(ShipKind.containerShip,214f,33.2f,270f,14.7f,10000f,distC);
//				Distribution distR = new Distribution(DistributionKind.normalDistribution
//						,150f,100,0f,300f,0f,50f);
//				Ship roro = new Ship(ShipKind.containerShip,214f,33.2f,90f,14.7f,10000f,distR);
//				Channel channel = new Channel(35046f,600f,2000f,0f,100f);
//				Na na = new Na(container,roro,channel);
//				textArea_1.append(container.toString()+"\r\n"+roro.toString());
//				textArea_1.append(CollisionFrequency.getCollisionFrequency(na)+"");
////				System.out.println(container.toString()+"\r\n"+roro.toString());
////				System.out.println(CollisionFrequency.getCollisionFrequency(na));
			}
		});
		button_4.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_4.setBounds(466, 10, 133, 18);
		contentPane.add(button_4);
		
		JLabel label_13 = new JLabel("\u6401\u6D45\u6982\u7387\u663E\u793A\uFF1A");
		label_13.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_13.setBounds(10, 604, 112, 25);
		contentPane.add(label_13);
		
		JButton button_5 = new JButton("\u8BA1\u7B97\u6401\u6D45\u6982\u7387");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
							+fileDirectionResult+fileNameResult;
					Connection conn = DriverManager.getConnection(dbur1, "username", "password");
					Statement stmt = conn.createStatement();
					 
					int tempCount = 0;
					float[] agroundFrenquency = new float[rowCount];
					float[] agroundFrenquency1 = new float[rowCount];
					float[] agroundFrenquency2 = new float[rowCount];
					float[] agroundFrenquency3 = new float[rowCount];
					for(int i = 0; i < rowCount; i++) {
						FOne fOne = new FOne(ship[i],channel[i]);
						FTwo fTwo = new FTwo(ship[i],channel[i]);
						FDrifting fDrifting = new FDrifting(ship[i],channel[i]);
						agroundFrenquency1[tempCount] = fOne.getFOneValue();
						agroundFrenquency2[tempCount] = fTwo.getFTwoValue();
						agroundFrenquency3[tempCount] = fDrifting.getFDriftingValue();
						agroundFrenquency[tempCount] = agroundFrenquency1[tempCount]
								+agroundFrenquency2[tempCount]+agroundFrenquency3[tempCount];
						textArea_2.append(ship[i].toString()+"\r\n");
						textArea_2.append("FOne"+agroundFrenquency1[tempCount]+"\r\n"
								+"FTwo"+agroundFrenquency2[tempCount]+"\r\n"
								+"FDrifting"+agroundFrenquency3[tempCount]+"\r\n"
								+"agroundFrenquency"+agroundFrenquency[tempCount]+"\r\n");
						String tempI = ship[i].toString();
						stmt.executeUpdate("insert into AgroundResult "
								+ "(ID,ShipKind,FOne,FTwo,FDrifting,AgroundFrequency) values "
								+ "('"+tempCount+"','"+tempI+
								"','"+agroundFrenquency1[tempCount]+
								"','"+agroundFrenquency2[tempCount]+
								"','"+agroundFrenquency3[tempCount]+
								"','"+agroundFrenquency[tempCount]+"')");
						tempCount++;
					}
					stmt.close();
					conn.close();
					JOptionPane.showMessageDialog( getParent(),
							"搁浅概率数据保存在"+fileDirectionResult+fileNameResult);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button_5.setFont(new Font("SimSun", Font.PLAIN, 15));
		button_5.setBounds(120, 606, 133, 22);
		contentPane.add(button_5);

	}
}
