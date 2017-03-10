package robots;

import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class RoboEwi
  extends AdvancedRobot
{
  static double prevEnergy = 100.0D;
  
  public RoboEwi() {}
  
  public void run() { setBodyColor(Color.green);
    setGunColor(Color.green);
    setRadarColor(Color.green);
    setScanColor(Color.green);
    setBulletColor(Color.green);
    for (;;)
    {
      if (getRadarTurnRemaining() == 0.0D)
        setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
      execute();
    }
  }
  
  public void onScannedRobot(ScannedRobotEvent e)
  {
    double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
    double radarTurn = absoluteBearing - getRadarHeadingRadians();
    
    setTurnRadarRightRadians(2.0D * Utils.normalRelativeAngle(radarTurn));
    

    setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - 
      getGunHeadingRadians() + e.getVelocity() * Math.sin(e.getHeadingRadians() - 
      absoluteBearing) / 90.0D));
    
    setFire(3.0D);
    
    if (prevEnergy - e.getEnergy() > 0.0D) {
      setAhead(200.0D);
      if (Math.random() > 0.8D) {
        setTurnRightRadians(Math.random() * 3.141592653589793D);
      } else {
        setTurnLeftRadians(Math.random() * 3.141592653589793D);
      }
    }
    
    prevEnergy = e.getEnergy();
  }
}