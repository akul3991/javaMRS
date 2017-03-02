package it.uniroma3.sii.javamrs.recommendations.utils;

import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class GenreRules {
	private static boolean belong(double value, double lower, double upper) {
		if (value > lower && value <= upper){
            return true;
        }
        else return false;
	}
	
	private static boolean belong2(double value, double lower, double upper){
        if (value >= lower && value < upper){
            return true;
        }
        else return false;
    }
	
	private static List<String> getSame(Big5Entity big, double value){
		List<String> lista=new ArrayList<String>();
		if(big.getApe()==value)
			lista.add("ope");
		if(big.getCosc()==value)
			lista.add("con");
		if(big.getEstr()==value)
			lista.add("ext");
		if(big.getGrad()==value)
			lista.add("agr");
		if(big.getNevr()==value)
			lista.add("neu");
		return lista;
	}
	
	public static List<String> getGenre(UserEntity user) {
		List<String> generi = new ArrayList<String>();
		
		Big5Entity big5 = user.getBig5Entity();
		String gender = user.getQuestionaryEntity().getSex();
		
		double ope = big5.getApe();
		double con = big5.getCosc();
		double ext = big5.getEstr();
		double agr = big5.getGrad();
		double neu = big5.getNevr();
		
		if(belong(con, 3.64, 3.76) && belong(ext, 3.635, 3.774) && belong(agr, 3.598, 3.731)){
            generi.add("jazz");
        }else if(belong(ope, 3.75, 3.875) && belong(agr,3.465, 3.598)){
            generi.add("reggae");
        }else if(belong(con, 3.64, 3.76) && belong(ext, 3.913, 4.052)){
            generi.add("salsa");
        }else if(belong(ope, 3.625, 3.75) && belong(con, 3.4, 3.52) && belong(ext, 3.496, 3.635)){
            generi.add("country");
        }else if(belong(ext, 3.635, 3.774) && belong(agr, 3.598, 3.731)&& belong(neu, 2.49, 2.61)){
            generi.add("jazz");
        }else if(belong(ext, 3.218, 3.357) && belong(neu, 2.97, 3.09)){
            generi.add("metal");
        }
		
		if(gender=="F"){
            if(belong(ope, 3.625, 3.75) && belong(ext, 3.496, 3.635) && belong(neu, 2.49, 2.61)){
                if(!generi.contains("country"))
                    generi.add("country");
            }else if(belong(ope, 4.25, 4.375) && belong(con, 3.42, 3.57)){
                generi.add("classic");
            }else if(belong(ope, 3.625, 3.75) && belong(con, 3.42, 3.57) && belong(ext, 2.49, 2.61)){
                if(!generi.contains("country"))
                	generi.add("country");
            }else if(belong(ope, 3.625, 3.75) && belong(con, 3.42, 3.57) && belong(agr, 3.652, 3.789)){
                if(!generi.contains("country"))
                	generi.add("country");
            }else if(belong(ope, 3.375, 3.5) && belong(neu, 2.91, 3.058)){
            	generi.add("r&b");
            }
        }else{
            if (belong(agr, 1, 2.915)){
                generi.add("r&b");
            }else if(belong(ope, 3.2, 3.35) && belong(ext, 3.47, 3.625) && belong(neu, 2.618, 2.776)){
                generi.add("rap");
            }else if(belong(ope, 3.35, 3.525) && belong(con,2.9, 3.05)){
                generi.add("pop");
            }else if(belong(ope, 3.7, 3.875) && belong(con, 3.35, 3.5) && belong(agr, 3.41, 3.575)){
                if(!generi.contains("country"))
                    generi.add("country");
            }else if(belong(ope, 3.35, 3.5) && belong(agr, 3.41, 3.575) && belong(neu, 2.144, 2.302)){
                if(!generi.contains("country"))
                    generi.add("country");
            }else if(belong(ope, 3.875, 4.05) && belong(agr, 2.915, 3.08)){
                if(!generi.contains("metal"))
                    generi.add("metal");
            }else if(belong(con, 2.9, 3.05) && belong(agr, 2.915, 3.08)){
                if(!generi.contains("metal"))
                    generi.add("metal");
            }
        }
		if(generi.size()==0){
            
            double max = Math.max(ope, Math.max(con, Math.max(ext, Math.max(agr, neu))));
            double min = Math.min(ope,Math.min(con, Math.min(ext,Math.min(agr, neu))));
            List<String> traits=new ArrayList<String>();
            if (min-1 < 5-max)
                traits=getSame(big5,min); //chiavi con il valore min
            else if (min-1 > 5-max)
                traits = getSame(big5, max); //chiavi con il valore max
            else
                traits = getSame(big5, min); //chiavi con il valore max e min
            for(String trait : traits) {
                switch (trait) {
                    case "ope":
                        if(belong2(ope, 1, 3)){

                            if(!generi.contains("r&b"))
                                generi.add("r&b");
                            if(!generi.contains("rap"))
                            	generi.add("rap");
                            if(!generi.contains("hip-hop"))
                            	generi.add("hip-hop");
                        }else{

                            if(!generi.contains("blues"))
                            	generi.add("blues");
                            if(!generi.contains("classic"))
                            	generi.add("classic");
                            if(!generi.contains("indi"))
                            	generi.add("indi");
                        }
                        break;
                    case "con":
                        if(belong2(con, 1, 3)){

                            if(!generi.contains("indi"))
                            	generi.add("indi");
                            if(!generi.contains("metal"))
                            	generi.add("metal");
                            if(!generi.contains("tecno"))
                            	generi.add("tecno");
                            if(!generi.contains("rap"))
                            	generi.add("rap");
                        }else{

                            if(!generi.contains("country"))
                            	generi.add("country");
                            if(!generi.contains("jazz"))
                            	generi.add("jazz");
                            if(!generi.contains("salsa"))
                            	generi.add("salsa");
                            if(!generi.contains("r&b"))
                            	generi.add("r&b");
                        }
                        break;
                    case "ext":
                        if(belong2(ext, 1, 3)){

                            if(!generi.contains("metal"))
                            	generi.add("metal");
                            if(!generi.contains("tecno"))
                            	generi.add("tecno");
                            if(!generi.contains("rock"))
                            	generi.add("rock");
                        }else{

                            if(!generi.contains("salsa"))
                            	generi.add("salsa");
                            if(!generi.contains("hip-hop"))
                            	generi.add("hip-hop");
                            if(!generi.contains("rap"))
                            	generi.add("rap");
                        }
                        break;
                    case "agr":
                        if(belong2(agr, 1, 3)){

                            if(!generi.contains("metal"))
                            	generi.add("metal");
                            if(!generi.contains("rap"))
                            	generi.add("rap");
                            if(!generi.contains("indi"))
                            	generi.add("indi");
                        }else{

                            if(!generi.contains("indi"))
                            	generi.add("indi");
                            if(!generi.contains("oldies"))
                            	generi.add("oldies");
                            if(!generi.contains("dance"))
                            	generi.add("dance");
                            if(!generi.contains("jazz"))
                            	generi.add("jazz");
                            
                        }
                        break;
                    case "neu":
                        if(belong2(neu, 1, 3)){

                            if(!generi.contains("salsa"))
                            	generi.add("salsa");
                            if(!generi.contains("jazz"))
                            	generi.add("jazz");
                            if(!generi.contains("hip-hop"))
                            	generi.add("hip-hop");
                        }else{

                            if(!generi.contains("indi"))
                            	generi.add("indi");
                            if(!generi.contains("metal"))
                                generi.add("metal");
                            if(!generi.contains("rock"))
                            	generi.add("rock");
                        }
                        break;
                }
            }
        }
		return generi;
	}
}
