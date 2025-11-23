{
  description = "Java Dev Shell";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = { self, nixpkgs }: let 
    pkgs = nixpkgs.legacyPackages."x86_64-linux";
  in
  {
    devShells."x86_64-linux".default = pkgs.mkShell {
      buildInputs = with pkgs; [
        jdk
      ];

      # Commands to run everytime the shell is activated
      shellHook = ''
        echo "Welcome"
        # git add -A
      '';
    };
  };
}

# To use run:
# > nix develop -c zsh
