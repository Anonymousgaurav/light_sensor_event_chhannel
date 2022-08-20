
import 'dart:async';

class LightBloc {

  double lightValue = 0;

  final _lightStreamController = StreamController<double>();

  StreamSink<double> get lightSink => _lightStreamController.sink;
  Stream<double> get lightStream => _lightStreamController.stream;

  void cancel() => _lightStreamController.close();
}