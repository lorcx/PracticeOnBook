
package think_in_java;
/**
 * 继承多个基础接口
 * @author dell
 *  monster怪物
 */
public interface Monster {
	/**
	 * 威胁
	 */
	void menace();
}

interface DangerousMonster extends Monster{
	/**
	 * 破坏消灭
	 */
	void destroy();	
}

/**
 * 致命的
 * @author dell
 *
 */
interface Lethal {
	void kill();
}

class DangerZilla implements DangerousMonster{

	@Override
	public void menace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}

/**
 * 吸血鬼
 * @author dell
 *
 */
interface vampire extends  DangerousMonster,Lethal{
	void drinkBlood();
}

class VeryBadVampire implements vampire{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drinkBlood() {
		// TODO Auto-generated method stub
		
	}
	
}

class HorrorShow {
   static void u(Monster m){
       m.menace();
   }
   static void v(DangerousMonster d){
       d.menace();
       d.destroy();
   }
   static void l(Lethal l){
       l.kill();
   }

   public static void main(String[] args) {
       DangerousMonster b = new DangerZilla();
       u(b);
       v(b);
   }
}
 
 
