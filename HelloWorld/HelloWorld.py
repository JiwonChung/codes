from evdev import InputDevice, categorize, ecodes

device = InputDevice("/dev/input/event3")
for event in device.read_loop():
    print(categorize(event))

