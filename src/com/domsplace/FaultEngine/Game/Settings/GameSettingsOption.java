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

package com.domsplace.FaultEngine.Game.Settings;

/**
 *
 * @author Dominic
 */
public class GameSettingsOption<T> {
    private String key;
    private T value;
    
    public GameSettingsOption() {
        this("error");
    }
    
    public GameSettingsOption(String key) {
        this(key, null);
    }
    
    public GameSettingsOption(String key, T value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {return this.key;}
    public T getValue() {return value;}
    
    public GameSettingsOption setValue(T value) {this.value = value; return this;}
}
