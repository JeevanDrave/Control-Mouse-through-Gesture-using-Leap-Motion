package leap;
/**
Author Name: Jeevan Bharat Drave
Email: G1drave@live.com
**/
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.leapmotion.leap.Gesture.Type;

public class HFC_MouseListener extends Listener 
{
	public Robot robot;
	
	public void onInit(Controller controller)
	{
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.config().setFloat("Gesture.KeyTap.MinDistance", 0.5f);
		//controller.config().setFloat("Gesture.KeyTap.MinDownVelocity", 30.0f);
		controller.config().save();
		System.out.println("Mouse activated");
	}
	//on leap device connected
	public void onConnect(Controller controller)
	{	}	
	
	//getting gestures performed on frame	
	public void onFrame(Controller controller)
	{
		Frame frame=controller.frame();		//giving device control to frame
		InteractionBox box=frame.interactionBox();		
		try
		{
			robot=new Robot();
		}
		catch(Exception e)
		{}
		
		//for moving mouse cursor over frame when right hand is enable and all fingers are not extended
		for(Hand hand:frame.hands())
		{
			if(hand.isRight())
			{
				for(Finger thumb:hand.fingers())
				{
					for(Finger index:hand.fingers())
					{
						for(Finger middle:hand.fingers())
						{
							for(Finger pinky:hand.fingers())
							{
								for(Finger ring:hand.fingers())
								{
									if(thumb.type()==Finger.Type.TYPE_THUMB)
									{
										if(index.type()==Finger.Type.TYPE_INDEX)
										{
											if(middle.type()==Finger.Type.TYPE_MIDDLE)
											{
												if(pinky.type()==Finger.Type.TYPE_PINKY)
												{
													if(ring.type()==Finger.Type.TYPE_RING)
													{
														if(!thumb.isExtended()&&!index.isExtended()&&!middle.isExtended()&&!pinky.isExtended()&&!ring.isExtended())
														{
															Vector handpos=hand.stabilizedPalmPosition();		
															Vector boxHandpos=box.normalizePoint(handpos);															
															Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
															robot.mouseMove((int)(screen.width*boxHandpos.getX()),(int)(screen.height-boxHandpos.getY()*screen.height));															
														}														
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		//for scrolling mouse using right hand enabled and only index finger is extended 
		for(Gesture gesture:frame.gestures())
		{
			for(Hand hand:frame.hands())
			{
				if(hand.isRight())
				{
					for(Finger thumb:hand.fingers())
					{
						for(Finger index:hand.fingers())
						{
							for(Finger middle:hand.fingers())
							{
								for(Finger pinky:hand.fingers())
								{
									for(Finger ring:hand.fingers())
									{
										if(thumb.type()==Finger.Type.TYPE_THUMB)
										{
											if(index.type()==Finger.Type.TYPE_INDEX)
											{
												if(middle.type()==Finger.Type.TYPE_MIDDLE)
												{
													if(pinky.type()==Finger.Type.TYPE_PINKY)
													{
														if(ring.type()==Finger.Type.TYPE_RING)
														{															
															if(!thumb.isExtended()&&index.isExtended()&&!middle.isExtended()&&!pinky.isExtended()&&!ring.isExtended())
															{															
																if(gesture.type()==Type.TYPE_CIRCLE)
																{
																	CircleGesture circle=new CircleGesture(gesture);
																	if(circle.pointable().direction().angleTo(circle.normal())<=Math.PI/3)
																	{
																		robot.mouseWheel(1);
																		try
																		{
																			Thread.sleep(50);
																		}
																		catch(Exception e)
																		{}
																		
																	}	
																	else
																	{
																		robot.mouseWheel(-1);
																		try
																		{
																			Thread.sleep(50);
																		}
																		catch(Exception e)
																		{}
																	}
																}															
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}			
		}
		//to perform left click and right click
		for(Gesture gesture:frame.gestures())
		{
			for(Hand hand:frame.hands())
			{
				if(hand.isLeft())
				{
					for(Finger thumb:hand.fingers())
					{
						for(Finger index:hand.fingers())
						{
							for(Finger middle:hand.fingers())
							{
								for(Finger pinky:hand.fingers())
								{
									for(Finger ring:hand.fingers())
									{
										if(thumb.type()==Finger.Type.TYPE_THUMB)
										{
											if(index.type()==Finger.Type.TYPE_INDEX)
											{
												if(middle.type()==Finger.Type.TYPE_MIDDLE)
												{
													if(pinky.type()==Finger.Type.TYPE_PINKY)
													{
														if(ring.type()==Finger.Type.TYPE_RING)
														{															
															if(!thumb.isExtended()&&index.isExtended()&&!middle.isExtended()&&!pinky.isExtended()&&!ring.isExtended())
															{															
																if(gesture.type()==Type.TYPE_KEY_TAP)
																{
																	robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
																	/*try
														            {
														            	Thread.sleep(50);
														            } 
														            catch(Exception e) 
														            {}*/
														            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
																}										
															}
															else if(!thumb.isExtended()&&index.isExtended()&&middle.isExtended()&&!pinky.isExtended()&&!ring.isExtended())
															{
																if(gesture.type()==Type.TYPE_KEY_TAP)
																{
																	robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
													                /*try
													                {
													                	Thread.sleep(50);
													                } 
													                catch(Exception e) 
													                {}*/
													                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
}
