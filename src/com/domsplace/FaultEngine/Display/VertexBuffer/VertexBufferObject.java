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

package com.domsplace.FaultEngine.Display.VertexBuffer;

import com.domsplace.FaultEngine.Display.Mesh.Mesh;
import com.domsplace.FaultEngine.Dispose.IDisposeable;

/**
 *
 * @author Dominic
 */
public final class VertexBufferObject implements IDisposeable {
    public Mesh mesh;
    public VertexBufferObject(final Mesh mesh) {
        this.mesh = mesh;
    }
    
    @Override
    public void dispose() {
        if(this.mesh != null) {
            this.mesh.dispose();
        }
        this.mesh = null;
    }
}
