/*
 * Copyright 2014 Dominic.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domsplace.FaultEngine.Language;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic
 */
public class Language {
    private static List<Language> LANGUAGES = new ArrayList<Language>();
    public static List<Language> getLanguages() {return new ArrayList<Language>(LANGUAGES);}
    
    public static Language getLanguageByRegionCode(String regionCode) {
        for(Language l : LANGUAGES) {
            if(l == null || l.regionCode == null || !l.regionCode.equals(regionCode)) continue;
            return l;
        }
        return null;
    }
    
    public static Language getLanguageByName(String name) {
        for(Language l : LANGUAGES) {
            if(l == null || l.name == null || !l.name.equals(name)) continue;
            return l;
        }
        return null;
    }
    
    public static Language getSystemLanguage() {
        return LANGUAGES.get(0);
    }
    
    //Languages
    public static Language UNITED_STATES_ENGLISH = new Language("English (United States)", "en-US");
    
    //Instance
    private String name;
    private String regionCode;
    private List<HumanText> humanTexts;
    
    private Language(String name, String regionCode) {
        this.name = name;
        this.regionCode = regionCode;
        this.humanTexts = new ArrayList<HumanText>();
        LANGUAGES.add(this);
    }
    
    public String getName() {return this.name;}
    public String getRegionCode() {return this.regionCode;}
    public List<HumanText> getDefinedHumanTexts() {return new ArrayList<HumanText>(this.humanTexts);}
    public HumanText getHumanTextByKey(String key) {return createHumanTextIfNotExists(key, "I am Error");}
    
    public void addHumanText(HumanText text) {this.humanTexts.add(text);}
    public void removeHumanText(HumanText text) {this.humanTexts.remove(text);}
    
    public HumanText createHumanText(String key, String text) {
        HumanText ht = new HumanText(this, key, text);
        this.addHumanText(ht);
        return ht;
    }
    
    public HumanText createHumanTextIfNotExists(String key, String text) {
        for(HumanText ht : this.getDefinedHumanTexts()) {
            if(ht == null) continue;
            if(ht.getKey() == null || !ht.getKey().equals(key)) continue;
            return ht;
        }
        return createHumanText(key, text);
    }
}
