/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import type {Node} from 'react';
import React from 'react';
import {
  SafeAreaView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import CalendarModule from './CalanderModule.js';
import LockscreenModule from './LockscreenModule.js';

const Section = ({children, title}): Node => {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
};

const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView
      style={[
        backgroundStyle,
        {
          justifyContent: 'space-evenly',
          alignItems: 'center',
          flex: 1,
        },
      ]}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <Text
        style={{fontSize: 20, fontWeight: 'bold'}}
        onPress={() => {
          CalendarModule.createCalendarEvent(
            'Birthday',
            'Home',
            (error, callbackResponse) => {
              if (error) {
                console.log('ERROR_FROM_NATIVE_MODULE');
              } else {
                console.log('SUCCESS_FROM_NATIVE_MODULE', callbackResponse);
              }
            },
          );
        }}>
        Single callback native code
      </Text>
      <Text
        style={{fontSize: 20, fontWeight: 'bold'}}
        onPress={() => {
          CalendarModule.createCalendarEventCallback(
            'testName',
            'testLocation',
            error => {
              console.error(`Error found! ${error}`);
            },
            eventId => {
              console.log(`event id ${eventId} returned`);
            },
          );
        }}>
        Double callback native code
      </Text>
      <Text
        style={{fontSize: 20, fontWeight: 'bold'}}
        onPress={async () => {
          try {
            const eventId = await CalendarModule.createCalendarEvent(
              'Party',
              'My House',
            );
            console.log(`Created a new event with id ${eventId}`);
          } catch (e) {
            console.error(e);
          }
        }}>
        Single callback native code
      </Text>
      <Text
        style={{fontSize: 20, fontWeight: 'bold'}}
        onPress={() => {
          console.log('LOCK_SCREEN_CODE1', LockscreenModule);

          setTimeout(() => {
            LockscreenModule.unlock();
            console.log('LOCK_SCREEN_CODE_ERR', LockscreenModule);
          }, 3000);
        }}>
        Lockscreen native code
      </Text>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
