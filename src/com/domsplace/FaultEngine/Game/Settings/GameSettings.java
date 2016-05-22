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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic
 */
public class GameSettings {
    private static GameSettings INSTANCE;
    public static GameSettings getSettings() {
        if(INSTANCE != null) return INSTANCE;
        return INSTANCE = new GameSettings();
    }
    
    //Instance
    private List<GameSettingsOption> options;
    
    private GameSettings() {
        this.options = new ArrayList<GameSettingsOption>();
    }
    
    public List<GameSettingsOption> getOptions() {return new ArrayList<GameSettingsOption>(this.options);}
    public GameSettingsOption getByKey(String key) {
        for(GameSettingsOption option : this.getOptions()) {
            if(option == null) continue;
            if(option.getKey().equals(key)) return option;
        }
        return null;
    }
    public GameSettingsOption getByKey(String key, Object value) {
        GameSettingsOption v = this.getByKey(key);
        if(v != null) return v;
        return new GameSettingsOption(key, value);
    }
}
