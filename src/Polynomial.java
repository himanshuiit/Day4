import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Polynomial 
{
	private Map<Integer,Integer> coeff;

	public Polynomial()
	{
		coeff=new TreeMap<Integer,Integer>();
	}
	
	public Polynomial(Map<Integer,Integer> inputMap)
	{
		this.coeff=new TreeMap<Integer, Integer>();
		for(Integer key: inputMap.keySet())
			this.getCoeff().put(key, inputMap.get(key));
	}
	
	public Map<Integer, Integer> getCoeff() 
	{
		return coeff;
	}
	
	public Polynomial(String input){
		coeff=new TreeMap<Integer, Integer>();
		input=input.replaceAll("-", "+-");
		String []split=input.split("\\+");
		for (String s :split){
			if(s.isEmpty())
				continue;
			if(!s.contains("x"))
				coeff.put(0, Integer.parseInt(s));
			else if(!s.contains("x^"))
				coeff.put(1, Integer.parseInt(s.substring(0,s.indexOf('x'))));
			else
				coeff.put(Integer.parseInt(s.substring(s.indexOf('^')+1)),Integer.parseInt(s.substring(0,s.indexOf('x'))));
		}
	}
	
	public Polynomial add(Polynomial pol2)
	{
		Polynomial result=new Polynomial();
		addRec(this,result);
		addRec(pol2,result);
		return result;
	}
	
	public void addRec(Polynomial pol,Polynomial result)
	{
		for(int key:pol.coeff.keySet())
		{
			if(!result.coeff.containsKey(key))
				result.coeff.put(key,0);
			result.coeff.put(key,result.coeff.get(key)+pol.coeff.get(key));
		}
	}
	
	public Polynomial multiply(Polynomial addend){
        Map<Integer, Integer> resultMap=new TreeMap<Integer,Integer>();
        Map<Integer, Integer> first=this.getCoeff();
        Map<Integer, Integer> second=addend.getCoeff();
        for(Map.Entry<Integer, Integer> term1: first.entrySet()){
            for(Map.Entry<Integer, Integer> term2: second.entrySet()){
            	int newExponent=term1.getKey()+term2.getKey();
            	if(resultMap.containsKey(newExponent))
            		resultMap.put(newExponent, resultMap.get(newExponent)+term1.getValue()*term2.getValue());
            	else
            		resultMap.put(newExponent, term1.getValue()*term2.getValue());
            }
        }
        return new Polynomial(resultMap);
    }
	
	public Polynomial subtract(Polynomial pol2)
	{
		Polynomial result=new Polynomial();
		for(int key:this.coeff.keySet())
		{
			result.coeff.put(key,this.coeff.get(key));
		}
		for(int key:pol2.coeff.keySet())
		{
			if(!result.coeff.containsKey(key))
				result.coeff.put(key,0);
			result.coeff.put(key,result.coeff.get(key)-pol2.coeff.get(key));
		}
		return result;
	}
	
	public String toString()
	{
		String result="";
		for(int key:this.coeff.keySet())
		{
			if(this.coeff.get(key)==0)
				continue;
			String c=""+this.coeff.get(key);
			if(this.coeff.get(key)>0)
				c="+"+c;
			result+=c+((key==0)?"":"x^"+key);
		}
		if(result.isEmpty())
			return "";
		result=result.replace("+1x","+").replace("-1x","-");
		if(result.charAt(0)=='+')
			return result.substring(1);
		return result;
	}
	
	
	
	public static void main(String[] a)
	{
		//Scanner s=new Scanner(System.in);
		Polynomial p1=new Polynomial("x");//s.nextLine());
		Polynomial p2=new Polynomial("-x");//s.nextLine());
		System.out.println("Added result :"+p1.add(p2));
		System.out.println("Difference : "+p1.subtract(p2));
		System.out.println("Product : "+p1.multiply(p2));
		//s.close();
	}
}
