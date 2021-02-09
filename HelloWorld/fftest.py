import evdev

# devices = [evdev.InputDevice(path) for path in evdev.list_devices()]
#
# for dev in devices:
#     print(dev.path, dev.name, dev.phys)
device = evdev.InputDevice('/dev/input/event18')
print(device)
for event in device.read_loop():
    # print(type(event.type))
    # if (event.code == evdev.ecodes.ABS_X) | (event.code == evdev.ecodes.ABS_Y):
    #     if event.type == evdev.events.EV_ABS:
    #         if event.type > 32767:
    #             print(event.value)
    # wheel
    if event.type == evdev.events.EV_ABS:
        if event.code == evdev.ecodes.ABS_X:
            value = (event.value - 32767)//73
            print("wheel : " + str(value))
        elif event.code == evdev.ecodes.ABS_Y:
            print("Acc : " + str(event.value))
        elif event.code == evdev.ecodes.ABS_Z:
            print("Break : " + str(event.value))