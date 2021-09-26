//
//  RCTCalendarModule.m
//  nativeLinking
//
//  Created by MadKoala on 25/09/21.
//

#import <Foundation/Foundation.h>
#import "RCTCalendarModule.h"
#import <React/RCTLog.h>

@implementation RCTCalendarModule

RCT_EXPORT_MODULE(CalendarModule);
// 1. CalendarModuleFoo this is name of your module that will be displayed in RN.
// 2. RCT_EXPORT_MODULE macro, exports and registers the native module class with React Native
// 3. RCT_EXPORT_MODULE macro also takes an optional argument that specifies the name that the module will be accessible as in your JavaScript code
RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location myCallback:(RCTResponseSenderBlock)callback)
// 4. React Native will not expose any methods in a native module to JavaScript unless explicitly told to. This can be done using the RCT_EXPORT_METHOD macro.
// 5. RCTResponseSenderBlock accepts only one argument - an array of parameters to pass to the JavaScript callback
{
 RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
  // 6. There are two approaches to error handling with callbacks.
  // The first is to follow Node’s convention and treat the first argument passed to the callback array as an error object.
  NSNumber *eventId = [NSNumber numberWithInt:123];
   callback(@[[NSNull null], eventId]);
}
// 7. Another option is to use two separate callbacks: onFailure and onSuccess.

RCT_EXPORT_METHOD(createCalendarEventCallback:(NSString *)title
                  location:(NSString *)location
                  errorCallback: (RCTResponseSenderBlock)errorCallback
                  successCallback: (RCTResponseSenderBlock)successCallback)
{
  @try {
    NSNumber *eventId = [NSNumber numberWithInt:123];
    successCallback(@[eventId]);
  }

  @catch ( NSException *e ) {
    errorCallback(@[e]);
  }
}

@end
