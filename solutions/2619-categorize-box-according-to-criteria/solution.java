class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
        if(isBulky(length,width,height) && isHeavy(mass)){
            return "Both";
        }
        else if(isBulky(length,width,height)){
            return "Bulky";
        }
        else if(isHeavy(mass)){
            return "Heavy";
        }
        return "Neither";
    }

    public boolean isBulky(int length,int width,int height){
        if(length >= 10000 || width >= 10000 || height>=10000){
            return true;
        }
       long volume = (long) length * width * height;
        return volume >= 1000000000;
    }

    public boolean isHeavy(int mass){
        if(mass >= 100){
            return true;
        }
        return false;
    }
}
