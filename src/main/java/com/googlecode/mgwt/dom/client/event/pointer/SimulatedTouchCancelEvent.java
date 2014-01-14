package com.googlecode.mgwt.dom.client.event.pointer;

import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;

public class SimulatedTouchCancelEvent extends TouchCancelEvent
{
  private final MsPointerCancelEvent event;

  public SimulatedTouchCancelEvent(MsPointerCancelEvent event) {
    this.event = event;
    setNativeEvent(event.getNativeEvent());
    setSource(event.getSource());
  }

}
