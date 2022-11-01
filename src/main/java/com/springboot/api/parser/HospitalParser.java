package com.springboot.api.parser;

import com.springboot.api.domain.Hospital;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HospitalParser implements Parser<Hospital> {

    // csv 파일에서 큰따옴표 안에 콤마가 있는 경우 처리 (RoadNameAddress)
    public static String[] csvSplit(String str){

        String[] resultStr=null;
        String result="";

        String[] a=str.split(",");

        int cnt=0;
        String temp="";

        for(int i=0;i<a.length;i++){
            if(a[i].indexOf("\"")==0){
                if(a[i].lastIndexOf("\"")==a[i].length()-1){
                    result+=a[i].replaceAll("\"","");
                }else{
                    cnt++;
                    temp+=a[i].replaceAll("\"","");
                }
            }else if(a[i].lastIndexOf("\"")==a[i].length()-1){
                if(cnt>0){
                    result+=temp+","+a[i].replaceAll("\"","");
                    cnt=0;
                    temp="";
                }
            }else{
                if(cnt>0){
                    cnt++;
                    temp+=","+a[i].replaceAll("\"","");
                }else{
                    result+=a[i];
                }
            }
            if(i!=a.length-1 && cnt==0)result+="|,|";
        }
        resultStr=result.split("\\|,\\|");

        return resultStr;

    }

    @Override
    public Hospital parse(String str) {
        String[] row = csvSplit(str);
//        System.out.println(Arrays.toString(row));
//        System.out.println(row[0] + " " + row[15] + " " + row[18] + " " + row[32]);

        Hospital hospital = new Hospital();
        try{
            hospital.setId(Integer.parseInt(row[0]));
        }catch(Exception e){
            e.printStackTrace();
        }
        hospital.setOpenServiceName(row[1].replace("\"", ""));

        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);
        int year = Integer.parseInt(row[5].substring(0, 4));
        int month = Integer.parseInt(row[5].substring(4, 6));
        int day = Integer.parseInt(row[5].substring(6, 8));
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0, 0));
        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
//        System.out.println("``" + row[19]);

        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Float.parseFloat(row[32]));

        return hospital;
    }
}
