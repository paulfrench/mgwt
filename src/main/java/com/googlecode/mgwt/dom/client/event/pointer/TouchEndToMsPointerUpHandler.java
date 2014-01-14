/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.dom.client.event.pointer;

import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;

/**
 * Convert TouchEndHandlers to MsPointerUpHandlers
 */
public class TouchEndToMsPointerUpHandler implements MsPointerUpHandler {
  private final TouchEndHandler handler;

  /**
   * <p>
   * Constructor for TouchEndToMsPointerUpHandler.
   * </p>
   * 
   * @param handler a {@link com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler} object.
   */
  public TouchEndToMsPointerUpHandler(TouchEndHandler handler) {
    this.handler = handler;
  }

  /** {@inheritDoc} */
  @Override
  public void onPointerUp(MsPointerUpEvent event) {
    SimulatedTouchEndEvent simulatedTouchEndEvent = new SimulatedTouchEndEvent(event, false);
    handler.onTouchEnd(simulatedTouchEndEvent);
  }

}
