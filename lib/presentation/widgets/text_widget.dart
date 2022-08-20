import 'package:flutter/material.dart';

class LightTextContainer extends StatelessWidget {
  const LightTextContainer({
    required this.value,
    Key? key,
  }) : super(key: key);

  final double value;

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Text(
          "Light value from sensor",
          textAlign: TextAlign.center,
          style: TextStyle(
              color: Colors.amber[600], fontSize: 20, fontFamily: DefaultTextStyle.of(context).style.fontFamily),
        ),
        const SizedBox(
          height: 30,
        ),
        Text("$value",
            textAlign: TextAlign.center,
            style:
                TextStyle(color: Colors.blue, fontSize: 30, fontFamily: DefaultTextStyle.of(context).style.fontFamily)),
      ],
    );
  }
}
