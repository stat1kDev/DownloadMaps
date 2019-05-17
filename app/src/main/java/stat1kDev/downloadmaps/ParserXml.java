package stat1kDev.downloadmaps;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Collections;

public class ParserXml {

    private Context context;

    public ParserXml(Context context){
        this.context = context;
    }



    public ArrayList<String> parserXmlForCountries() {
        ArrayList<String> listNamesCountries = new ArrayList<>();

        try {

            XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.regions);

            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

                switch (xmlPullParser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_DOCUMENT");
                        }
                        break;
                    }

                    case XmlPullParser.START_TAG: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_TAG: имя тега = "
                                    + xmlPullParser.getName()
                                    + ", уровень = "
                                    + xmlPullParser.getDepth()
                                    + ", число атрибутов = "
                                    + xmlPullParser.getAttributeCount());
                        }

                        if (xmlPullParser.getName().equals("region")) {
                            if (xmlPullParser.getAttributeValue(null, "lang") != null) {
                                listNamesCountries.add(xmlPullParser.getAttributeValue(null, "name"));
                            }

                        }
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "END_TAG: имя тега = " + xmlPullParser.getName());
                        }
                        break;
                    }
                    default:
                        break;

                }
                xmlPullParser.next();

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return sortAndUpperCaseList(listNamesCountries);
    }

    public ArrayList<String> parserXmlForCountriesWithRegions() {
        ArrayList<String> listNamesCountriesWithRegions = new ArrayList<>();

        try {

            XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.regions);

            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

                switch (xmlPullParser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: {
                        /*if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_DOCUMENT");
                        }*/
                        break;
                    }

                    case XmlPullParser.START_TAG: {
                        /*if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_TAG: имя тега = "
                                    + xmlPullParser.getName()
                                    + ", уровень = "
                                    + xmlPullParser.getDepth()
                                    + ", число атрибутов = "
                                    + xmlPullParser.getAttributeCount());
                        }*/

                        if (xmlPullParser.getName().equals("region")) {
                            if (xmlPullParser.getDepth() == 3 && xmlPullParser.getAttributeValue(null, "join_map_files") != null) {
                                listNamesCountriesWithRegions.add(xmlPullParser.getAttributeValue(null, "name"));
                            }
                            if (xmlPullParser.getDepth() == 3 && xmlPullParser.getAttributeValue(null, "map") != null) {
                                listNamesCountriesWithRegions.add(xmlPullParser.getAttributeValue(null, "name"));
                            }
                        }
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        /*if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "END_TAG: имя тега = " + xmlPullParser.getName());
                        }*/
                        break;
                    }
                    default:
                        break;

                }
                xmlPullParser.next();

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        sortAndUpperCaseList(listNamesCountriesWithRegions);
        return listNamesCountriesWithRegions;
    }


    private ArrayList<String> sortAndUpperCaseList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, Character.toUpperCase(list.get(i).charAt(0)) + list.get(i).substring(1).toLowerCase());
        }

        Collections.sort(list);
        return list;
    }

}
