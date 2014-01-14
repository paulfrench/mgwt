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
package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBaseCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * Base class for all buttons
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public abstract class ButtonBase extends TouchWidget implements HasText {

  private boolean eventPreventPropagation = true;
  private boolean eventPreventDefault = true;

  /**
   * Construct a base button with a given css
   * 
   * @param css the css to use for this button
   */
  public ButtonBase(ButtonBaseCss css) {
    this(DOM.createDiv(), css);
  }

  private final String active;

  /**
   * Construct a button with a given element and css
   * 
   * @param element the element to use
   * @param css the css to use
   */
  public ButtonBase(Element element, ButtonBaseCss css) {
    setElement(element);
    css.ensureInjected();
    this.active = css.active();

    addTouchHandler(new TouchHandler() {

      @Override
      public void onTouchCanceled(TouchCancelEvent event) {
        applyEventPropagationSettings(event);
        removeStyleName(active);
        if (!MGWT.getOsDetection().isIOs()) {
          DOM.releaseCapture(getElement());
        }
      }

      @Override
      public void onTouchEnd(TouchEndEvent event) {
        applyEventPropagationSettings(event);
        removeStyleName(active);
        if (!MGWT.getOsDetection().isIOs()) {
          DOM.releaseCapture(getElement());
        }
      }

      @Override
      public void onTouchMove(TouchMoveEvent event) {
        applyEventPropagationSettings(event);
      }

      @Override
      public void onTouchStart(TouchStartEvent event) {
        applyEventPropagationSettings(event);
        addStyleName(active);
        if (!MGWT.getOsDetection().isIOs()) {
          DOM.setCapture(getElement());
        }

      }
    });

    addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        removeStyleName(active);
      }
    });

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasText#getText()
   */
  /** {@inheritDoc} */
  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
   */
  /** {@inheritDoc} */
  @Override
  public void setText(String text) {
    getElement().setInnerText(text);

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.googlecode.mgwt.ui.client.widget.touch.TouchWidget#addTapHandler(com.googlecode.mgwt.dom
   * .client.event.tap.TapHandler)
   */
  /** {@inheritDoc} */
  @Override
  public HandlerRegistration addTapHandler(TapHandler handler) {
    return super.addTapHandler(handler);
  }

  /**
   * Touch start, touch move, touch cancel and touch end are propagated to the parent element
   * when eventPreventPropagation == false. This widget starts to receive all events when
   * touch start is raised and will continue to receive touch events until touch
   * end is raised regardless where the finger/pointer finishes up on the device screen.
   * The default is true, touch events are not propagated. If a Tap event
   * is raised then this is propagated regardless.
   * @param eventPropagation
   */
  public void setTouchEventsPreventPropagation(boolean eventPreventPropagation) {
    this.eventPreventPropagation = eventPreventPropagation;
  }

  /**
   * Sets whether the default browser behaviour of the touch events captured by this widget
   * are prevented. The default is true.
   * @param eventPreventDefault
   */
  public void setTouchEventsPreventDefault(boolean eventPreventDefault) {
    this.eventPreventDefault = eventPreventDefault;
  }

  private void applyEventPropagationSettings(DomEvent<?> event) {
    if (eventPreventPropagation) {
      event.stopPropagation();
    }
    if (eventPreventDefault) {
      event.preventDefault();
    }
  }
}
