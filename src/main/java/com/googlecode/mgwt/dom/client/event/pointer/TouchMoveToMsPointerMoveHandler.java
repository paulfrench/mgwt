/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.dom.client.event.pointer;

import com.googlecode.mgwt.dom.client.event.pointer.SimulatedTouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;

/**
 * Convert TouchMoveHandlers to MsPointerMoveHandlers for pointer devices
 *
 */
public class TouchMoveToMsPointerMoveHandler implements MsPointerMoveHandler, MsPointerDownHandler, MsPointerUpHandler {

  private boolean ignoreEvent;
	private final TouchMoveHandler touchMoveHandler;

	/**
	 * <p>Constructor for TouchMoveToMsPointerMoveHandler.</p>
	 *
	 * @param touchMoveHandler a {@link com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler} object.
	 */
	public TouchMoveToMsPointerMoveHandler(TouchMoveHandler touchMoveHandler) {
		this.touchMoveHandler = touchMoveHandler;
    ignoreEvent = true;
	}

	/** {@inheritDoc} */
	@Override
  public void onPointerMove(MsPointerMoveEvent event) {
    if (ignoreEvent)
      return;
		touchMoveHandler.onTouchMove(new SimulatedTouchMoveEvent(event));
	}

  @Override
  public void onPointerUp(MsPointerUpEvent event)
  {
    ignoreEvent = true;
  }

  @Override
  public void onPointerDown(MsPointerDownEvent event)
  {
    ignoreEvent = false;
  }

}
