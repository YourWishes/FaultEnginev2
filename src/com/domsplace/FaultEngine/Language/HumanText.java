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

/**
 *
 * @author Dominic
 */
public class HumanText {
    private String key;
    private Language language;
    private String text;
    
    public HumanText(Language forWhatLanguage, String messageKey, String text) {
        this.key = messageKey;
        this.language = forWhatLanguage;
        this.text = text;
    }
    
    public Language getLanguage() {return this.language;}
    public String getKey() {return this.key;}
    public String getText() {return this.text;}
    
}
