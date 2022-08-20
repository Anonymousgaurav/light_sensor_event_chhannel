import 'package:flutter/material.dart';
import 'package:light_sensor_event_channel_flutter/data/lightBloc.dart';
import 'package:light_sensor_event_channel_flutter/presentation/home_page.dart';
import 'package:provider/provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(primarySwatch: Colors.blue),
        home: Provider<LightBloc>(
            create: (ctx) => LightBloc(),
            dispose: (context, bloc) => bloc.cancel(),
            child: const HomePage()
        )
    );
  }
}

